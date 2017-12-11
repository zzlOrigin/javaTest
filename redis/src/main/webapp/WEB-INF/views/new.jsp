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
    <link rel="stylesheet" type="text/css" href="/static/js/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/js/datetimepicker/css/bootstrap-datetimepicker.min.css">
</head>
<body>
    <div class="container">
        <form method="post" enctype="multipart/form-data">
            <legend>添加抢购商品</legend>
            <div class="form-group">
                <label>商品名称</label>
                <input type="text" name="productName" class="form-control">
            </div>
            <div class="form-group">
                <label>商品标题</label>
                <input type="text" name="title" class="form-control">
            </div>
            <div class="form-group">
                <label>市场价格</label>
                <input type="text" name="marketPrice" class="form-control">
            </div>
            <div class="form-group">
                <label>抢购价</label>
                <input type="text" name="nowPrice" class="form-control">
            </div>
            <div class="form-group">
                <label>商品图片</label>
                <input type="file" name="tupian" class="form-control">
            </div>
            <div class="form-group">
                <label>开始时间</label>
                <input type="text" name="sTime" class="form-control timePicker">
            </div>
            <div class="form-group">
                <label>结束时间</label>
                <input type="text" name="eTime" class="form-control timePicker">
            </div>
            <div class="form-group">
                <label>库存数量</label>
                <input type="text" name="productNum" class="form-control">
            </div>
            <div class="form-group">
                <label>商品详情</label>
                <textarea id="editer" name="productList" class="form-control"></textarea>
            </div>
    `       <div class="form-group">
                <button class="btn btn-success btn btn-lg">新增商品</button>
            </div>
        </form>
    </div>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/static/js/editer/scripts/module.js"></script>
    <script src="/static/js/editer/scripts/hotkeys.js"></script>
    <script src="/static/js/editer/scripts/uploader.js"></script>
    <script src="/static/js/editer/scripts/simditor.js"></script>
    <script>
        $(function(){
            var timepicker = $('.timePicker').datetimepicker({
                format: "yyyy-mm-dd hh:ii",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true
            });

            var editor = new Simditor({
                textarea:$('#editer')
            });
        });
    </script>
</body>
</html>