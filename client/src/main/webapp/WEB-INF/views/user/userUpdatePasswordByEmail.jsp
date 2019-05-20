<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>用户注册</title>
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
                <form class="form-horizontal m-t" id="signupForm" action="${ctx}/user/doUpdatePwdByEmail" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-9">
                            <input id="username" name="username" class="form-control" type="text" aria-required="true" aria-invalid="true" placeholder="请输入您的用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码：</label>
                        <div class="col-sm-9">
                            <input id="password" name="password" class="form-control" type="password" placeholder="请输入您的密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电子邮箱：</label>
                        <div class="col-sm-9">
                            <input id="email" name="email" class="form-control" type="email" placeholder="请输入您的电子邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" style="width: 300px;height: 30px;" class="btn btn-success block full-width m-b">修 改</button>
                        </div>
                    </div>
                    <p class="text-muted text-center col-sm-offset-3 col-sm-9"><small>想起密码了？</small><a href="${ctx}/user/login">点此登录</a></p>
                </form>
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
<!-- jQuery Validation plugin javascript-->
<script src="${ctx }/js/user/jquery.validate.min.js"></script>
<script src="${ctx }/js/user/messages_zh.min.js"></script>
<script src="${ctx }/js/user/email-true.js"></script>
</body>
</html>