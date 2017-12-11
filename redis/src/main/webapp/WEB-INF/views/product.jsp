<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .clock{
            font-size: 22px;
            color: red;
            margin-top: 30px;
        }
    </style>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">
                    <i class="fa fa-shopping-basket"></i> ProductStore
                </a>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <img src="http://ozp49ovem.bkt.clouddn.com/${product.image}?imageView2/1/w/300/h/300" alt="商品图片">
            </div>
            <div class="col-md-8">
                <h4>${product.productName}</h4>
                <p>${product.title}</p>
                <h3 class="text-danger">抢购价：￥${product.nowPrice}&nbsp;&nbsp;&nbsp;&nbsp;<small style="text-decoration:line-through">市场价：￥${product.marketPrice}</small></h3>
            </div>
            <c:choose>
                <c:when test="${product.isstart() and not product.isend()}">
                    <button id="killBtn" class="btn btn-default btn-lg">立即抢购</button>
                </c:when>
                <c:when test="${product.isend()}">
                    <button class="btn btn-lg btn-danger" disabled>已结束</button>
                </c:when>
                <c:when test="${product.productNum == 0}">
                    <button class="btn btn-lg btn-danger" disabled>已售罄</button>
                </c:when>
                <c:otherwise>
                    <button id="killBtn" class="btn btn-lg btn-danger" disabled>等待抢购</button>
                    <div class="clock">距离抢购时间：<span id="clock">xx分xx秒</span></div>
                </c:otherwise>
            </c:choose>
            

        </div>
        <div class="row">
            <div class="col-md-offset-4 col-md-8">
                <div>
                    ${product.productList}
                </div>
            </div>
        </div>

    </div>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/moment.js"></script>
    <script src="/static/js/jquery.countdown.min.js"></script>
    <script src="/static/js/layer/layer.js"></script>
    <script>
        $(function(){
            $("#clock").countdown(${product.time},function (event) {
                $(this).html(event.strftime('%D天%H小时%M分钟%S秒'));
            }).on("finish.countdown",function () {
                $("#killBtn").text("立即抢购").removeAttr("disabled");
            });
            $("#killBtn").click(function (resp) {
                $.get("/product/seckill/${product.id}").done(function (resp) {
                    if (resp.state == 'success'){
                        layer.alert("抢购成功");
                    }else{
                        layer.alert(resp.message)
                    }

            }).error(function () {
                layer.msg("服务器异常");
            });
            });
        });
    </script>
</body>
</html>