<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>登陆</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/login.css"/>
</head>

<body style="background-image: none;">

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
                <form method="post" id="loginForm" action="${ctx}/user/login" class="form-signin">
                    <h4 style="color: #333;font-weight: normal;" class="no-margins">请登录：</h4>
                    <p style="color: #333;font-weight: normal;margin-bottom: 0px;" class="m-t-md">进入知识就是财富</p>
                    <%
                    String s = request.getParameter("state");
                    Integer state = -1;
                    if(s!=null){
                        state = Integer.valueOf(s);
                    }
                    %>
                    <input type="hidden" value="<%=state%>" id="state">
                    <p style="color: red" id="jsshow"></p>
                    <input style="margin-bottom: 8px;" type="text" class="form-control uname " placeholder="用户名" name="username" required autofocus/>
                    <input style="margin-bottom: 8px;" type="password" class="form-control pword m-b" placeholder="密码" name="password" required/>
                    <button type="submit" class="btn btn-success btn-block">登录</button>
                    <div class="form-inline" style="margin-top: 5px">
                        <input type="checkbox" name="remember">自动登陆
                        <a style="margin-left: 65%" href="${ctx}/user/updatePassword">忘记密码</a>
                    </div>
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
<script>
    $(function () {
        var state = parseInt($("#state").val());
        if(state==0){
            $("#jsshow").text("该用户未激活")
        }else if (state == 1){
            $("#jsshow").text("用户名密码错误")
        }
    });
</script>
</body>
</html>