<%@ page import="cn.edu.qdu.common.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
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
    <%--<%
        Page page1= (Page) request.getAttribute("page");
        List result = page1.getResult();
        ObjectMapper mapper = new ObjectMapper();
        String userInfo = mapper.writeValueAsString(result);
    %>--%>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <%--<%@include file="/common/admin-admin-nav.jsp" %>--%>
    <div class="row" style="margin-top: 10px;margin-bottom: 10px;margin-left: 70px;">
        <label for="titile">名称</label><input type="text" id="titile"/>
        <label style="margin-left: 30px" for="model">型号</label>
        <select type="text" id="model">
                <option value="">全部</option>
            <c:forEach items="${types}" var="type">
                <option value="${type.id}">${type.name}</option>
            </c:forEach>
        </select>
        <button class="btn btn-primary" style="margin-left: 20px;" onclick="search()">筛选</button>
    </div>
    <div class="row">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td style="width: 80px;"></td>
                    <td>名称</td>
                    <td>型号</td>
                    <td>后缀</td>
                    <td>价格</td>
                    <td>热度</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody id="test">
                <c:forEach items="${page.result}" var="product">
                    <tr>
                        <td></td>
                        <td>${product.title}</td>
                        <td>${product.model.name}</td>
                        <td>${product.postfix}</td>
                        <td>${product.point}</td>
                        <td>${product.stock}</td>
                        <td>
                            <a href="${ctx}/user/product/edit/${product.id}">修改</a>
                            <a href="${ctx}/user/product/show/${product.id}" style="margin-left: 5px;">查看销量</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
        <a style="height: 25px;width: 101px;float: right;" class="btn btn-primary" href="${ctx}/user/product/new">添加</a>

        <div class="text-center" style="margin-left: 60px;">
            <tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
        </div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<%--<%--%>
    <%--Integer i = (Integer) request.getAttribute("current");--%>
<%--%>--%>
<script>
    <%--var pageNum = JSON.parse('<%=i%>');--%>
    function search() {
        var titile = $("#titile").val();
        var model  = $("#model").val();
        var url = "/user/storage?ps=5";
        if(titile){
            url+="&titile="+titile
        }
        if(model){
            url+="&model="+model
        }
        location.href=url;
    }
</script>
</body>
</html>