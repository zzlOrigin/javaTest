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
</head>
<body>

    <u1>上传文件</u1>
    <form action="http://upload-z1.qiniup.com" method="post" enctype="multipart/form-data">
        <input type="hidden" name="token" value="${upToken}">
        <input type="file" name="file">
        <button>上传文件</button>
    </form>
</body>
</html>