<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>下单成功</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <div class="row">
        <div id="delSuccess" class="alert alert-success">下单成功...付款中請稍等</div>
        <input type="hidden" id="orderId" value="${orderId}">
    </div>
    <div class="row">
        <div class="progress" id="hid" style="display: none; width: 100%;">
            <div id="scroll" class="progress-bar" role="progressbar" aria-valuenow="60"
                 aria-valuemin="0" aria-valuemax="100" style="width: 10%;">
            </div>
        </div>
    </div>
    <div class="row text-center">
            <a class="btn btn-info" href="${ctx}/product/">继续购买</a>
            <a style="margin-left: 5px;" class="btn btn-info" href="${ctx}/order/">查看订单</a>
    </div>
</div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp" %>
<script>
    $(function () {
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
        var orderId = $("#orderId").val();
        localStorage.setItem("cart","0");
        $("#cart").html("Cart(0)");
        $.ajax({
            url: ctx + "/order/pay/" + orderId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("付款成功......请前往订单中心下载......");
                    $("#delSuccess").text("下单成功...请前往订单中心下载");
                } else {
                    $("#delSuccess").text("下单失败...请检查余额后...前往订单中心完成支付");
                    $("#delSuccess").removeClass("alert-success");
                    $("#delSuccess").addClass("alert-danger");
                    toastr.error("下单失败...请检查余额后...前往订单中心完成支付");
                }
            },
            error: function () {
                toastr.error("发生错误...报歉...请关闭浏览器重新登录下单");
            }
        })
    });
</script>
</body>
</html>