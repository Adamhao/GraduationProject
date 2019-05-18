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
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="username" class="form-control" type="text" name="username" value="${user.username}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="email" class="control-label">邮箱</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="email" class="form-control" type="text" name="email" value="${user.email}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <label for="phone" class="control-label">电话</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="phone" readonly class="form-control" type="text" name="phone" value="${user.phone}"/>
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
                <label for="address" class="control-label">地址</label>
                <div class="">
                    <input style="color:black;font-family: fantasy;font-size: 15px;" id="address" class="form-control" type="text" name="address" value="${user.address}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-offset-3" style="width: 55%;">
                <div class="text-center">
                    <button style="margin-right: 2px;float: right;height: 25px;width: 101px;" type="submit" class="btn btn-primary">保存</button>
                    <a style="margin-right: 2px;float: right;height: 25px;width: 101px;" class="btn btn-success" href="${ctx }/user/recharge">充值</a>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>