<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h3 class="page-header">
        ${kaola.productName}
        <a href="/product/${kaola.id}/edit" class="btn btn-info pull-right">编辑</a>
        <a href="javascript:;" id="delLink" rel="${kaola.id}" class="btn btn-danger pull-right">删除</a>
    </h3>
    <ul>
        <li>产地 ： ${kaola.place}</li>
        <li>市场价： ${kaola.marketPrice}</li>
        <li>考拉价: ${kaola.price}</li>
        <li>评论数量: ${kaola.commentNum}</li>
        <li>类型 ： </li>
    </ul>
    <a href="/hello">返回列表</a>
</div>
</body>
</html>