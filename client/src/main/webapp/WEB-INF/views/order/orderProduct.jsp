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
    <%@include file="/common/user-admin-nav.jsp" %>
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <td style="width: 80px;"></td>
                <td>名称</td>
                <td>型号</td>
                <td>价格</td>
                <td>库存</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.result}" var="product">
                <tr>
                    <td></td>
                    <td>${product.title}</td>
                    <td>${product.model.name}</td>
                    <td>${product.point}</td>
                    <td>${product.stock}</td>
                    <td>
                        <a class="btn btn-primary" href="${ctx }/user/product/download?url=${product.url}">下载</a>
                        <c:if test="${product.url.endsWith('doc')||product.url.endsWith('docx')}">
                            <a class="btn btn-primary" style="color: #fff;" onclick="showPdf('${product.url}')">在线查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center" style="margin-left: 70px;">
        <tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
    </div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
<script>
    function showPdf(url){
        window.open("/product/devDoc?url="+url);
    }
</script>
</html>