<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <a href="/product/new" class="btn btn-primary">添加</a>
        <table class="table">
            <thead>
                <tr>
                    <th>商品名称</th>
                    <th>产地</th>
                    <th>价格</th>
                    <th>市场价格</th>
                    <th>评论数量</th>
                </tr>
            </thead>
       <c:forEach items="${kaolas}" var="kaola">
           <tr>
               <td><a href="/product/${kaola.id}">${kaola.productName}</a></td>
               <td>${kaola.place}</td>
               <td>${kaola.price}</td>
               <td>${kaola.marketPrice}</td>
               <td>${kaola.commentNum}</td>
           </tr>
       </c:forEach>
        </table>
    </div>
</body>
</html>