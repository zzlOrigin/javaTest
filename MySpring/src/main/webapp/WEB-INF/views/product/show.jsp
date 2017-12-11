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
        <h3>${kaola.productName}</h3>
            <a href="/product/${kaola.id}/edit" class="btn btn-default pull-right">编辑</a>
            <a href="javascript:;" id="delink" rel="${kaola.id}" class="btn btn-danger pull-right">删除</a>
        <ul>
            <li>产地:${kaola.place}</li>
            <li>市场价:${kaola.marketPrice}</li>
            <li>考拉价:${kaola.price}</li>
            <li>评论数量: ${kaola.commentNum}</li>
        </ul>
        <a href="/product">返回列表</a>
    </div>
<script src="/static/js/jquery.js"></script>
    <script>
        $(function(){
            $("#delink").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确定要删除吗")){
                    window.location.href="/product/"+id+"/delete";
                }
            });
        });
    </script>
</body>
</html>