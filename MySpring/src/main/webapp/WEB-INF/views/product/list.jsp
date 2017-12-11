<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>

    <div class="container">
        <div class="Panel panel-default">
            <h3 class="panel-title">查询</h3>
        </div>
        <div class="panel-body">
        <form class="form-inline">
            <input value="${param.productName}" type="text" name="productName" placeholder="商品名称" class="form-control">
            <select name="typeId" class="form-control">
                <option value="">--请选择--</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.id}" ${param.typeId == type.id ? 'selected' : ''}>${type.typeName}</option>
                </c:forEach>
            </select>
            <button class="btn btn-default">搜索</button>
        </form>
        </div>
        <c:if test="${not empty message}">
            <div class="alert alert-info">
                ${message}
            </div>
        </c:if>
<a href="/product/new" class="btn btn-danger">新增商品</a>
    <table class="table">
        <tr>
            <th>商品名称</th>
            <th>商品分类</th>
            <th>产地</th>
            <th>市场价</th>
            <th>考拉价</th>
            <th>评论数量</th>
        </tr>
        <c:forEach items="${pageInfo.list}" var="kaola">
            <tr>
                <td><a href="/product/${kaola.id}">${kaola.productName}</a></td>
                <td>${kaola.kaolaType.typeName}</td>
                <td>${kaola.place}</td>
                <td>${kaola.marketPrice}</td>
                <td>${kaola.price}</td>
                <td>${kaola.commentNum}</td>
            </tr>
        </c:forEach>

    </table>
        <ul id="pagination-demo" class="pagination-sm"></ul>
    </div>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePages: 10,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}&productName="+encodeURIComponent('${param.productName}') + "&typeId=${param.typeId}"
        });
    });
</script>
</body>
</html>