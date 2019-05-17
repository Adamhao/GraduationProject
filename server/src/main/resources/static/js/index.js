
$(function() {

    $.ajax({
        type: 'POST',
        url: '/echarts/queryEachTypeSales',
        success: function(result) {
            // 基于准备好的dom，初始化echarts实例
            var myChart1 = echarts.init(document.getElementById('charts1'));

        // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '各类别销售量'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };
            option.xAxis.data = result.xAxisData;
            option.series.data = result.seriesData;
            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option);
            // console.log(result);
        },
        dataType: 'json'
    });


    //////////////////////////////////////////////////////////////////

    $.ajax({
        type: 'POST',
        url: '/echarts/queryEachTypeQuantity',
        success: function(result) {
            var myChart2 = echarts.init(document.getElementById('charts2'));
            var option2 = {
                title : {
                    text: '各类别下商品数量',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)",
                    labelLine :{show:true}
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
                },
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:335, name:'直接访问'},
                            {value:310, name:'邮件营销'},
                            {value:234, name:'联盟广告'},
                            {value:135, name:'视频广告'},
                            {value:1548, name:'搜索引擎'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            option2.series = [
                {
                    name: '各类别商品数量',
                    type: 'pie',
                    radius : '65%',
                    center: ['50%', '60%'],
                    data: result.seriesData,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ];
            option2.legend.data = result.legendData;
            myChart2.setOption(option2,true);
        },
        dataType: 'json'
    });

//////////////////////////////////////////////////////////////////

});

