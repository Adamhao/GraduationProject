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
<div class="container">
    <div class="middle-box text-center animated fadeInDown" style="margin-top: -140px;width: 80%;">
        <div style="margin-top: 20%">
            <h3 class="col-sm-offset-3 col-sm-9" style="color: #333">欢迎加入知识就是财富</h3>
            <p class="col-sm-offset-3 col-sm-9" >成为新的用财富获取知识的人</p>
            <form class="form-horizontal m-t" id="signupForm" action="${ctx }/user/addUser" method="post">
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
                    <label class="col-sm-3 control-label">确认密码：</label>
                    <div class="col-sm-9">
                        <input id="confirm_password" name="confirm_password" class="form-control" type="password" placeholder="请再次输入您的密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">电子邮箱：</label>
                    <div class="col-sm-9">
                        <input id="email" name="email" class="form-control" type="email" placeholder="请输入您的电子邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">手机号码：</label>
                    <div class="col-sm-6">
                        <input id="phone" name="telPhone" class="form-control" type="tel" placeholder="请输入您的手机号码">
                    </div>
                    <button type="button" id="checkPhone" class="btn btn-primary" onclick="check()" style="width: 150px;height: 30px;">发送验证码</button>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">验证码：</label>
                    <div class="col-sm-9">
                        <input id="proveCode" name="proveCode" class="form-control" type="text" placeholder="请输入您的手机验证码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" style="width: 300px;height: 30px;" class="btn btn-success block full-width m-b">注 册</button>
                    </div>
                </div>
                <p class="text-muted text-center col-sm-offset-3 col-sm-9"><small>已经有账户了？</small><a href="${ctx}/user/login">点此登录</a></p>
            </form>

        </div>
    </div>
</div>
<!-- /container -->

<%@ include file="/common/include-base-js.jsp" %>
<script src="${ctx }/js/login.js" type="text/javascript"></script>
<!-- jQuery Validation plugin javascript-->
<script src="${ctx }/js/user/jquery.validate.min.js"></script>
<script src="${ctx }/js/user/messages_zh.min.js"></script>
<script src="${ctx }/js/user/form-validate-demo.js"></script>
<script>
    //存放时间间隔
    var count = 0;
    //存放定时器
    var flag = null;
    //定时器调用的函数
    function done(){
        if(count==0){
            clearInterval(flag);
        }
        else{
            count=count-1;
        }
    }
    function check() {
        if (count == 0) {
            $.ajax({
                url: "/user/checkPhone",
                type: "post",
                data: {
                    phone: function () {
                        return $("#phone").val();
                    }
                },
                dataType: "json",
                success: function (data) {
                    alert("短信已发送请查收。");
                }
            });
            count=60;
            flag=setInterval(done,1000);
        }else{
            alert("还需要"+(count)+"秒才能点击");
        }
    }
</script>
</body>
</html>