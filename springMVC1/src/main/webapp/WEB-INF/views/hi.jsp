<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 周晓龙
  Date: 2017/11/2
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
        <div class="container">
        <c:if test="${not empty message}">
            <div class="alert alert-success">
            正在审核
            </div>
        </c:if>
        <form method="post" action="/hi" enctype="multipart/form-data">
            <div class="form-group">
            名字:<input name="userName" type="text" class="form-control">
            </div>
            <div class="form-group">
            密码:<<input type="password" name="password" class="form-control">
            </div>
            <div class="form-group">
            上传图片:<input name="photo"  type="file" class="form-control">
            </div>
            <button>提交</button>
        </form>
        </div>
</body>
</html>
