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
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h3>抢购列表</h3>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="/new" class="btn btn-primary">新增商品</a>
            </div>
            <div class="panel-body">
                <div class="row">
                    <c:forEach items="${products}" var="product">
                        <div class="row">
                            <div class="col-md-3">
                                <img src="http://ozp49ovem.bkt.clouddn.com/${product.image}?imageView2/1/w/150/h/150" alt="商品图片" width="180px" height="200px">
                            </div>
                            <div class="col-md-9">
                                <h4><a href="/product/${product.id}">${product.productName}</a></h4>
                                <p>抢购价:${product.nowPrice}</p>
                                <h4>开始时间:<fmt:formatDate value="${product.startTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></h4>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

</body>
</html>