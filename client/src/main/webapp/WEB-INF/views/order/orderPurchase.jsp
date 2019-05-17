<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>订单确认</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <form:form cssClass="form-horizontal" action="${ctx}/order/ordering" method="post">

        <div class="row">
            <table class="table table-striped">
                <thead>
                <tr>
                    <td>名称</td>
                    <td>型号</td>
                    <td>后缀</td>
                    <td>价格</td>
                    <td>数量</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.cart}" var="cartItem">
                    <c:set var="ci" value="${cartItem.value}"></c:set>
                    <tr pid="${ci.product.id}">
                        <td>${ci.product.title}</td>
                        <td>${ci.product.model.name}</td>
                        <td>${ci.product.postfix}</td>
                        <td>${ci.product.point}</td>
                        <td>${ci.total}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="row text-right" style="display: block;width: 100%;">
                <a class="btn" href="${ctx}/cart/">回购物车修改</a>
                <button style="height: 25px;width: 101px;" class="btn btn-primary" type="submit" href="${ctx}/order/ordering/">确认</button>
            </div>
            <div id="delSuccess" class="alert alert-success" style="display: none;">删除成功...</div>
            <div id="delAllSuccess" class="alert alert-success" style="display: none;">购物车清空成功...</div>
        </div>
    </form:form>
</div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp" %>
<script src="${ctx }/js/cart.js" type="text/javascript"></script>
</body>
</html>