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
                <label for="balance" class="control-label">余额</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="balance" readonly class="form-control" type="text" name="balance" value="${user.balance}"/>
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
                <label for="point" class="control-label">充值金额</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="num" class="form-control" type="number" name="num" />
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
                    <button style="margin-right: 2px;float: right;height: 25px;width: 101px;" type="button" class="btn btn-primary" onclick="doCharge()">充值</button>
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
                console.log(1);
                console.log(data.code===200);
                if(data.code===200){
                    location.href = "${ctx }/user/profile";
                }
            },
            error: function (data) {
                console.log(2);
                console.log(data.code===200);
                if(data.code===200){
                    location.href = "${ctx }/user/profile";
                }
            }
    });
    }
</script>
</html>