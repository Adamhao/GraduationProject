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
    <style>
        .rating-container {
            display:inline-block !important;
            float:right !important;
        }
    </style>
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

        <hr style="margin-top:50px;margin-bottom:50px;">

        <div class="row">
            <div class="col-md-10 text-align">
                <h3>评论</h3>
                <hr/>
                <c:if test="${comments == null || empty comments}">
                    <h5>暂无评价</h5>
                </c:if>
                <c:forEach var="comment" items="${comments}" varStatus="status">

                    <!-- First Comment -->
                    <article class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="panel panel-default arrow left">
                                <div class="panel-body">
                                    <header class="text-left">
                                        <div class="comment-user"><i class="fa fa-user"></i> ${comment.username}
                                            <input type="number" class="rating" min=0 max=5 step=1 data-size="xs" readonly="readonly" value="${comment.rate}">
                                        </div>
                                        <time class="comment-date" datetime=""><i class="fa fa-clock-o"></i><fmt:formatDate value="${comment.commentTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </time>
                                    </header>
                                    <hr>
                                    <div class="comment-post">
                                        <p>
                                            <strong>${comment.content == null || comment.content == '' ? "买家默认评价": comment.content}</strong>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </article>

                </c:forEach>

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
        $(function() {

            $("input.rating").each(function(index,element) {
                $(element).rating('refresh',{
                    showClear:false
                });
            });

        });
    </script>
</body>
</html>