<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>订单详情</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>名称</td>
                <td>型号</td>
                <td>总价</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${order.orderItems}" var="orderItem">
                <tr pid="${orderItem.id}">
                    <td><a href="${ctx}/product/${orderItem.product.id}">${orderItem.product.title}</a></td>
                    <td>${orderItem.product.model.name}</td>
                    <td>${orderItem.product.point}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status==4}">
                                <a class="btn btn-primary" href="${ctx }/user/product/download?url=${orderItem.product.url}">下载</a>
                                <c:if test="${orderItem.product.url.endsWith('doc')||orderItem.product.url.endsWith('docx')}">
                                    <a class="btn btn-primary" style="color: #fff;" onclick="showPdf('${orderItem.product.url}')">在线查看</a>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" style="color: #fff" >付款后可下载查看</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp" %>
<script>
    function showPdf(url){
        window.open("/product/devDoc?url="+url);
    }
</script>
</body>
</html>