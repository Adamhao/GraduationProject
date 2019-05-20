<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>忘记密码</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/login.css"/>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container" style="margin-top: 100px;">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-5">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1 style="color: #333;font-weight: normal;font-size: 39px">知识就是财富</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4 style="color: #333;font-weight: normal;">欢迎使用 <strong>知识就是财富</strong> 电商系统</h4>
                    <strong style="color: #333;font-weight: normal;">还没有加入？ <a href="${ctx}/user/reg">立即成为我们的一员吧</a></strong>
                </div>
            </div>
            <div class="col-sm-5" style="box-shadow: 0 0 0 1px #e5e5e5;border-top-right-radius: 2px;padding: 15px;margin-top: 2px;border-top-left-radius: 2px;">
                <div class="form-signin">
                    <label class="control-label">推荐使用手机号码进行修改密码</label> <br />
                    <a href="${ctx}/user/updatePwdByTel" class="btn btn-primary" style="width: 150px;height: 30px;text-align: center;font-size: small;color: white">通过手机找回</a>
                    <label class="control-label">若手机不在身边，请通过邮箱修改密码</label> <br />
                    <a href="${ctx}/user/updatePwdByEmail" class="btn btn-primary" style="width: 150px;height: 30px;text-align: center;font-size: small;color: white">通过邮箱找回</a>
                </div>
            </div>

        </div>
        <div class="signup-footer">
            <div class="pull-left" style="color: #333;font-weight: normal;">
                月入百万不是梦，心动不如快行动！
            </div>
        </div>
    </div>

</div>

<!-- /container -->

<%@ include file="/common/include-base-js.jsp" %>
<script src="${ctx }/js/login.js" type="text/javascript"></script>
</body>
</html>