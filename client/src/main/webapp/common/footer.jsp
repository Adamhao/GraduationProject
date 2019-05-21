<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="footer_overlay"></div>
<footer class="footer">
    <%--相对路径跳转后影响加载--%>
    <%--<div class="footer_background" style="background-image:url(../static/images/footer.jpg)"></div>--%>
    <div class="footer_background" style="background-image:url(/static/images/footer.jpg)"></div>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="footer_content d-flex flex-lg-row flex-column align-items-center justify-content-lg-start justify-content-center">
                    <div class="footer_logo"><a href="#">主页</a></div>
                    <div class="copyright ml-auto mr-auto">
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> <a href="http://39.108.213.225:18081/admin/" target="_blank">Java11</a>
                    </div>
                    <%--<div class="footer_social ml-lg-auto">
                        <ul>
                            <li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                            <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                            <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                        </ul>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- /footer -->