<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/global.jsp"%>
<title>${product.title} 商品信息</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include-base-styles.jsp"%>
<link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
</head>

<body>
    <%@include file="/common/header-nav.jsp" %>
	<div class="container">
        <div class="row">
            <div class="col-md-5 text-center">
                <img class="img-responsive img-rounded" src="${ctx}${product.masterPic.url}">
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <label>商品名称：</label>${product.title}
                </div>
                <div class="form-group">
                    <label>商品型号：</label>${product.model.name}
                </div>
                <div class="form-group">
                    <label>商品价格：</label>${product.point}
                </div>
                <div class="form-group">
                    <label>商品简介：</label>${product.note}
                </div>
                <p>
                    <a class="btn btn-primary btn-lg  btn-block addCart" productid="${product.id}" style="color: #fff;" role="button" onclick="cart()">加入购物车</a>
                </p>
            </div>
        </div>
    </div>


    </div> <!-- /container -->

    <%@include file="/common/footer.jsp" %>
	<%@ include file="/common/include-base-js.jsp"%>
    <script src="${ctx }/js/product.js" type="text/javascript"></script>
    <script>
        function cart() {
            var a = parseInt(localStorage.getItem("cart"));
            a++;
            localStorage.setItem("cart",""+a);
            $("#cart").html("Cart("+a+")");
        }
    </script>
</body>
</html>