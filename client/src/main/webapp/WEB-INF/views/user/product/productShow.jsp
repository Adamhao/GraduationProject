<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>商品管理</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/product.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <%--<%@include file="/common/admin-admin-nav.jsp" %>--%>
    <div class="row">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td>名称</td>
                    <td>价格</td>
                    <td>类别</td>
                    <td>热度</td>
                    <td>订单编号</td>
                    <td>下单时间</td>
                    <td>购买时间</td>
                    <td>购买者</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.result}" var="product">
                    <tr>
                        <td>${product.title}</td>
                        <td>${product.point}</td>
                        <td>${product.model.name}</td>
                        <td>${product.stock}</td>
                        <td>${product.orderNumber}</td>
                        <td>${product.createTime}</td>
                        <td>${product.payTime}</td>
                        <td>${product.user.username}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
        <div class="text-center">
            <tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
        </div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>