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
    <link rel="stylesheet" href="/static/bootstarp/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h3>${kaola.productName}</h3>
        <ul>
            <li>商品价格：${kaola.price}</li>
            <li>市场价格:${kaola.marketPrice}</li>
            <li>产地:${kaola.place}</li>
            <li>评论数量:${kaola.commentNum}</li>
            <li>商品分类:${kaola.kaolaType.typeName}</li>
        </ul>
        <a href="/edit/${kaola.id}" class="btn btn-warning">修改</a>
        <a href="/dele/${kaola.id}" class="btn btn-danger">删除</a>
    </div>
</body>
</html>