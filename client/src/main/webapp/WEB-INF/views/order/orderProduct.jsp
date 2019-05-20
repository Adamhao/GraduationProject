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
                <td>热度</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.result}" var="oi">
                <tr>
                    <td></td>
                    <td>${oi.product.title}</td>
                    <td>${oi.product.model.name}</td>
                    <td>${oi.product.point}</td>
                    <td>${oi.product.stock}</td>
                    <td>
                        <a class="btn btn-primary" href="${ctx }/user/product/download?url=${oi.product.url}">下载</a>
                        <c:if test="${oi.product.url.endsWith('doc')||oi.product.url.endsWith('docx')||oi.product.url.endsWith('xls')||oi.product.url.endsWith('xlsx')||oi.product.url.endsWith('ppt')}">
                            <a class="btn btn-primary" style="color: #fff;" onclick="showPdf('${oi.product.url}')">在线查看</a>
                        </c:if>
                        <c:if test="${oi.status==1}">
                            <a class="btn btn-primary" style="color: #fff;" onclick="doComment('${oi.id}')">评价</a>
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

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">商品评价</h4>
                </div>
                <div class="modal-body">
                    <table class="table text-center">
                        <tr class="order-item" id="orderDetails"></tr>
                    </table>

                    <hr class="simple" style="color:#6f5499" />

                    <label for="input-id">商品评分:</label>
                    <input id="input-id" type="number" class="rating" min=0 max=5 step=1 data-size="md" >

                    <div class="area">
                        <div class="form-group">
                            <label for="content">商品评价</label>
                            <textarea class="form-control chackTextarea" id="content" name="content" rows="3"></textarea>
                        </div>
                        <div class="box01-num">
                            <input type="hidden" name="areaLength" id="areaLength"/>
                            <p>你还可以输入<b style="display:inline;" class="num">140</b>字</p>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="comment()">发表评价</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
<script>
    function showPdf(url){
        window.open("/product/devDoc?url="+url);
    }
    function doComment(){
        $("#myModal").modal('show');
    }
</script>
<!-- /container -->
<%@include file="/common/footer.jsp" %>

</body>
</html>