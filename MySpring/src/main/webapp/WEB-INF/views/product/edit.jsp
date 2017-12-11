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
    <form method="post">
        <input type="hidden" name="id" value="${kaola.id}">
        <div class="form-group">
            <label>商品名称</label>
            <input type="text" name="productName" value="${kaola.productName}" class="form-control">
        </div>
        <div class="form-group">
            <label>产地</label>
            <input type="text" name="place" value="${kaola.place}" class="form-control">
        </div>
        <div class="form-group">
            <label>市场价</label>
            <input type="text" name="marketPrice" value="${kaola.marketPrice}" class="form-control">
        </div>
        <div class="form-group">
            <label>考拉价</label>
            <input type="text" name="price" value="${kaola.price}" class="form-control">
        </div>
        <div class="form-group">
            <label>所属类型</label>
            <select name="typeId" class="form-control">
                <option value="">--请选择类型--</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.id}" ${type.id == kaola.typeId ? 'selected' : ''}>${type.typeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <button class="btn btn-success">修改</button>
            <a href="/product/${kaola.id}">返回</a>
        </div>
    </form>
</div>
</body>
</html>