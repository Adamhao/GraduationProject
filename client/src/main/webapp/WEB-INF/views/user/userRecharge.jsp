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
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container" style="margin-top: 30px;">
    <%--<%@include file="/common/user-admin-nav.jsp" %>--%>
        <input id="userId" type="hidden" value="${user.id}">
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="username" class="control-label">账号</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="username" readonly class="form-control" type="text" name="username" value="${user.username}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="bank" class="control-label">银行卡号</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="bank" class="form-control" type="text" name="bank" value="${user.bank}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="point" class="control-label">积分</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="point" readonly class="form-control" type="text" name="point" value="${user.point}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="point" class="control-label">充值套餐包</label>
                <div class="">
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" style="width: 100px;height: 50px;" class="btn btn-default"><div></div>10积分</button>
                        <button type="button" style="width: 100px;height: 50px;" class="btn btn-default">50积分</button>
                        <button type="button" style="width: 100px;height: 50px;" class="btn btn-default">100积分</button>
                        <button type="button" style="width: 100px;height: 50px;" class="btn btn-default">200积分</button>
                        <button type="button" style="width: 100px;height: 50px;" class="btn btn-default">500积分</button>
                        <button type="button" style="width: 100px;height: 50px;" class="btn btn-default">积分充满</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="progress col-sm-offset-3" id="hid" style="display: none; width: 55%;">
                <div id="scroll" class="progress-bar" role="progressbar" aria-valuenow="60"
                     aria-valuemin="0" aria-valuemax="100" style="width: 10%;">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <div class="text-center">
                    <button style="margin-right: 2px;float: right;height: 25px;width: 101px;" type="button" class="btn btn-primary" onclick="doCharge()">银行卡充值</button>
                    <button style="margin-right: 2px;float: right;height: 25px;width: 101px;" type="button" class="btn btn-primary" onclick="Alipay()">支付宝充值</button>
                </div>
            </div>
        </div>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
<script>
    function doCharge() {
        $("#hid").show();
        $("#scroll").css("width","10%");
        $("#scroll").css("width","20%");
        $("#scroll").css("width","30%");
        $("#scroll").css("width","30%");
        $("#scroll").css("width","40%");
        $("#scroll").css("width","50%");
        $("#scroll").css("width","60%");
        $("#scroll").css("width","70%");
        $("#scroll").css("width","80%");
        $("#scroll").css("width","90%");
        $("#scroll").css("width","100%");
        var userId = $("#userId").val();
        var num  = $("#num").val();
        if(!num){
            num=0;
        }
        $.ajax({
            type: "POST",
            url: "/user/recharge/"+userId+"/"+num,
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            dataType: "json",
            success: function(data){
                if(data.code===200){
                    location.href = "${ctx }/user/profile";
                }
            },
            error: function (data) {
                if(data.code===200){
                    location.href = "${ctx }/user/profile";
                }
            }
    });
    }

    function Alipay() {
        var num  = $("#num").val();
        if(!num){
            num=0;
        }
        window.open("/pay/in?title=积分充值&price="+num+"&desc=大哥真有钱");
    }
</script>
</html>