<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>商品列表</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <div class="row" style="margin-top: 10px;margin-bottom: 10px;margin-left: 50px;">
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
        <c:forEach items="${page.result}" var="product">
            <c:set var="task" value="${leave.task }"/>
            <c:set var="pi" value="${leave.processInstance }"/>
            <div class="col-md-3 col-sm-4 col-xs-6 text-center">
                <a href="${ctx}/product/${product.id}"><img class="img-thumbnail" src="${ctx}${product.masterPic.url}"
                                                            style="width: 140px; height: 140px;"></a>

                <p>${product.title}</p>

                <p class="price">$${product.point}</p>
                <p>（${product.postfix}）     <span class="price">Hot：${product.stock}</span></p>

                <p>
                    <a class="btn btn-info" href="${ctx}/product/${product.id}" role="button">查看</a>
                    <a class="btn btn-primary addCart" productid="${product.id}" role="button" href="#" onclick="cart()">购买</a>
                </p>
            </div>
        </c:forEach>
    </div>
    <div class="text-center" style="margin-left: 40%;">
        <tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
    </div>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp" %>
<script src="${ctx }/js/product.js" type="text/javascript"></script>
<script>
    function cart() {
        var a = parseInt(localStorage.getItem("cart"));
        a++;
        localStorage.setItem("cart",""+a);
        $("#cart").html("Cart("+a+")");
    }
</script>
<%--<%--%>
    <%--Integer i = (Integer) request.getAttribute("current");--%>
<%--%>--%>
<script>
    <%--var pageNum = JSON.parse('<%=i%>');--%>
    function search() {
        var titile = $("#titile").val();
        var model  = $("#model").val();
        var url = "/product/?ps=8";
        if(titile){
            url+="&title="+titile
        }
        if(model){
            url+="&model="+model
        }
        location.href=url;
    }
</script>
</body>
</html>