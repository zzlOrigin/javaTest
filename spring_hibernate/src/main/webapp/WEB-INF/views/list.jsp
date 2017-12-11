<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" method="">
                    <input type="text" placeholder="商品名称" name="p_like_s_productName" value="${param.p_like_s_productName}" class="form-control">
                    <input type="text" placeholder="商品价格或者市场价格" name="p_eq_bd_price_or_marketPrice" value="${p_eq_bd_price_or_marketPrice}" class="form-control">
                    <button class="btn btn-primary">搜索</button>
                </form>
            </div>
        </div>
        <a href="/new" class="btn btn-success">添加商品</a>
        <table class="table">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>价格</th>
                <th>市场价</th>
                <th>产地</th>
                <th>评论数量</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${pages.items}" var="kaola">
                    <tr>
                        <td><a href="/kaola/${kaola.id}">${kaola.productName}</a></td>
                        <td>${kaola.price}</td>
                        <td>${kaola.marketPrice}</td>
                        <td>${kaola.place}</td>
                        <td>${kaola.commentNum}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul id="pagination-demo" class="pagination-sm"></ul>
    </div>
    <script src="/static/bootstarp/js/jquery.min.js"></script>
    <script src="/static/bootstarp/js/bootstrap.min.js"></script>
    <script src="/static/bootstarp/js/jquery.twbsPagination.js"></script>
    <script>
        $(function () {
            var url = location.search.substr(1)
            $('#pagination-demo').twbsPagination({
                totalPages: ${pages.pageTotal},
                visiblePages: 5,
                first:'首页',
                last:'末页',
                prev:'上一页',
                next:'下一页',
                href:"http://localhost:8080/?p_like_s_productName=${param.p_like_s_productName}&p_eq_bd_price_or_marketPrice=${param.p_eq_bd_price_or_marketPrice}&pageNo={{number}}"
            });
        });
    </script>
</body>
</html>