<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>商品修改</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/product.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <%--<%@include file="/common/admin-admin-nav.jsp" %>--%>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form:form id="inputForm" role="form" action="${ctx}/user/product/edit"
                       method="post" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="hidden" name="id" value="${product.id}"/>
                    <input type="hidden" name="postfix" value="${product.postfix}"/>
                    <input type="hidden" name="inputUser.id" value="${product.inputUser.id}"/>
                    <%--<input type="hidden" name="picUrl" value="${product.picUrl}"/>--%>
                    <label class="col-sm-2 control-label">商品名称</label>
                    <div class="col-sm-10">
                        <input minlength="6" required class="form-control" value="${product.title}" type="text" id="title" name="title"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品价格</label>

                    <div class="col-sm-10">
                        <input type="number" required class="form-control" type="text" value="${product.point}" id="point" name="point"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品型号</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="model" name="model.id">
                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}" >${type.name}</option>
                            </c:forEach>
                            <input type="hidden" value="${product.model.id}" id="up"/>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品热度</label>
                    <div class="col-sm-10">
                        <input class="form-control" required readonly type="number" value="${product.stock}" id="stock" name="stock"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="pic">封面图片</label>
                    <div class="col-sm-10">
                        <img class="img-responsive img-rounded" width="300px" src="${ctx}${product.masterPic.url}">
                        <input type="file" id="pic" name="imgFile">
                        <p class="help-block">图片不能超过1MB.</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="file">项目文档</label>
                    <div class="col-sm-10">
                        <input type="file" id="file" name="file">
                        <p class="help-block">如果是多文档请上传压缩文件</p>
                    </div>
                </div>
                <input type="hidden" name="url" value="${product.url}">
                <input type="hidden" name="time" value="${product.createTime.time}">
                <input type="hidden" name="masterPic.id" value="${product.masterPic.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品简介：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="note">${product.note}</textarea>
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<script>
    $(document).ready(function(){
        var x = document.getElementById("up").value;
        $("#model").val(x);
    });
</script>

</body>
</html>