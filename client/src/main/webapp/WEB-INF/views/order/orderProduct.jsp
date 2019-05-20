<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>商品管理</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/product.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <%@include file="/common/user-admin-nav.jsp" %>
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <td style="width: 80px;"></td>
                <td>名称</td>
                <td>型号</td>
                <td>价格</td>
                <td>热度</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.result}" var="oi">
                <tr>
                    <td></td>
                    <td>${oi.product.title}</td>
                    <td>${oi.product.model.name}</td>
                    <td>${oi.product.point}</td>
                    <td>${oi.product.stock}</td>
                    <td>
                        <a class="btn btn-info" href="${ctx }/user/product/download?url=${oi.product.url}">下载</a>
                        <c:if test="${oi.product.url.endsWith('doc')||oi.product.url.endsWith('docx')||oi.product.url.endsWith('xls')||oi.product.url.endsWith('xlsx')||oi.product.url.endsWith('ppt')}">
                            <a class="btn btn-info" style="color: #fff;"
                               onclick="showPdf('${oi.product.url}')">在线查看</a>
                        </c:if>
                        <c:if test="${oi.status==1}">
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal" id="oi${oi.id}"
                                    onclick="comment('${oi.id}','${oi.product.title}','${oi.product.postfix}','${oi.product.point}')">
                                评价
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center" style="margin-left: 70px;">
        <tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">商品评价</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table class="table text-center">
                        <tr class="order-item" id="orderDetails">
                            <td id="title"></td>
                            <td id="postfix"></td>
                            <td id="point"></td>
                        </tr>
                    </table>

                    <hr class="simple" style="color:#6f5499"/>

                    <label for="input-id">商品评分:</label>
                    <input id="input-id" type="number" class="rating" min=0 max=5 step=1 data-size="md">
                    <input type="hidden" id="orderId">
                    <div class="area">
                        <div class="form-group">
                            <label for="content">商品评价</label>
                            <textarea class="form-control chackTextarea" id="content" name="content"
                                      rows="3"></textarea>
                        </div>
                        <div class="box01-num">
                            <input type="hidden" name="areaLength" id="areaLength"/>
                            <p>你还可以输入<b style="display:inline;" class="num">140</b>字</p>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="close" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="doComment()">发表评价</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
<script>
    $(function () {
        var txtobj = {
            divName: "area", //外层容器的class
            textareaName: "chackTextarea", //textarea的class
            numName: "num", //数字的class
            num: 140 //数字的最大数目
        };

        var textareaFn = function () {
            //定义变量
            var $onthis;//指向当前
            var $divname = txtobj.divName; //外层容器的class
            var $textareaName = txtobj.textareaName; //textarea的class
            var $numName = txtobj.numName; //数字的class
            var $num = txtobj.num; //数字的最大数目

            function isChinese(str) {  //判断是不是中文
                var reCh = /[u00-uff]/;
                return !reCh.test(str);
            }

            function numChange() {
                var strlen = 0; //初始定义长度为0
                var txtval = $.trim($onthis.val());
                for (var i = 0; i < txtval.length; i++) {
                    if (isChinese(txtval.charAt(i)) == true) {
                        strlen = strlen + 2;//中文为2个字符
                    } else {
                        strlen = strlen + 1;//英文一个字符
                    }
                }
                strlen = Math.ceil(strlen / 2);//中英文相加除2取整数
                if ($num - strlen < 0) {
                    $par.html("超出 <b style='display:inline;color:red;font-weight:lighter' class=" + $numName + ">" + Math.abs($num - strlen) + "</b> 字"); //超出的样式
                }
                else {
                    $par.html("还可以输入 <b style='display:inline;' class=" + $numName + ">" + ($num - strlen) + "</b> 字"); //正常时候
                }
                $b.html($num - strlen);
                $("#areaLength").val(strlen);
            }

            $("." + $textareaName).on("focus", null, function () {
                $b = $(this).parents("." + $divname).find("." + $numName); //获取当前的数字
                $par = $b.parent();
                $onthis = $(this); //获取当前的textarea
                var setNum = setInterval(numChange, 300);
            });
        }
        textareaFn();

    });
    $('#myModal').on('shown.bs.modal', function () {
        // $('#myInput').trigger('focus')
    });

    function showPdf(url) {
        window.open("/product/devDoc?url=" + url);
    }

    function comment(id, title, postfix, point) {
        $("#orderId").val(id);
        $("#title").text(title);
        $("#postfix").text(postfix);
        $("#point").text(point);
    }

    function doComment() {
        var id = $("#orderId").val();
        var rate = $("#input-id").val();
        var content = $("#content").val();
        $.ajax({
            url: "${ctx}/product/addComment",
            type:"POST",
            data:{
              id:id,
              rate:rate,
              content:content
            },
            success: function () {
                $("#close").click();
                $("#oi"+id).hide();
                $(".clear-rating").click();
                $("#content").val("");
            }
        });
    }
</script>
<!-- /container -->
<%@include file="/common/footer.jsp" %>

</body>
</html>