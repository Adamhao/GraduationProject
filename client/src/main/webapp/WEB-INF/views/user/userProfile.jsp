<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>${title}</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/user.js" type="text/javascript"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=a5193mPqYAGrdbEAfiOu8YSuXcMj4QA7"></script>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container" style="margin-top: 30px;">
    <%--<%@include file="/common/user-admin-nav.jsp" %>--%>

    <form method="post" id="loginForm" action="${ctx}/user/userProfile/update" class="form-signin">
        <input hidden type="text" name="id" value="${user.id}">
        <input hidden type="text" name="password" value="${user.password}">
        <input hidden type="text" name="state" value="${user.state}">
        <input hidden type="text" name="time" value="${user.createTime.time}">
        <input hidden type="text" name="activation_code" value="${user.activation_code}">
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="username" class="control-label">账号</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="username" class="form-control"
                           type="text" name="username" value="${user.username}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="email" class="control-label">邮箱</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="email" class="form-control"
                           type="text" name="email" value="${user.email}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="phone" class="control-label">电话</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="phone" readonly
                           class="form-control" type="text" name="phone" value="${user.phone}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="bank" class="control-label">银行卡号</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="bank" class="form-control"
                           type="text" name="bank" value="${user.bank}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="point" class="control-label">积分</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="point" readonly
                           class="form-control" type="text" name="point" value="${user.point}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="suggestId" class="control-label">地址</label>
                <div class="r-result">
                    <input type="hidden" id="add" value="${user.address}">
                    <input id="suggestId" style="color:black;font-family: fantasy;font-size: 15px;" class="form-control"
                           type="text" name="address" />
                    <div id="l-map"></div>
                    <div id="searchResultPanel"
                         style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <div class="text-center">
                    <button style="margin-right: 2px;float: right;height: 25px;width: 101px;" type="submit"
                            class="btn btn-primary">保存
                    </button>
                    <a style="margin-right: 2px;float: right;height: 25px;width: 101px;" class="btn btn-success"
                       href="${ctx }/user/recharge">充值</a>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<script type="text/javascript">
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }

    var map = new BMap.Map("l-map");
    map.centerAndZoom("北京", 12);                   // 初始化地图,设置城市和地图级别。

    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
        {
            "input": "suggestId",
            "location": map
        });

    ac.addEventListener("onhighlight", function (e) {  //鼠标放在下拉列表上的事件
        console.log(12);
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        console.log(_value);
        myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
        console.log("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
        G("searchResultPanel").innerHTML = "onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

        setPlace();
    });

    function setPlace() {
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun() {
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }

        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }

    function some(){
        var address = $("#add").val();
        G("searchResultPanel").innerHTML = "onconfirm<br />index = " + 0 + "<br />myValue = " + address;
    }
    some();
</script>
</body>
</html>