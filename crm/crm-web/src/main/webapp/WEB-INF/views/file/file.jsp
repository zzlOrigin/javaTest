<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <%@include file="../include/css.jsp"%>
    <style>
        tr{
            height: 50px;
            line-height: 50px;
        }
        .table>tbody>tr>td{
            vertical-align: middle;
        }
        .file_icon {
            font-size: 30px;
        }
        .table>tbody>tr:hover{
            cursor: pointer;
        }
        .webuploader-container {
            display: inline-block;
        }
        .webuploader-pick {
            padding: 5px 10px;
            overflow: visible;
            font-size: 12px;
            line-height:1.5;
            font-weight: 400;
        }
    </style>
    <link rel="stylesheet" href="/static/plugins/webUploader/webuploader.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
   <%@include file="../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="file"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">${file.name}</h3>
                    <div class="box-tools pull-right">
                    <c:if test="${not empty file}">
                        <a href="/file?pid=${file.pid}" class="btn btn-default btn-sm"><i class="fa fa-arrow-left"></i>返回上级</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${file.type == 'file'}">
                            <a href="/file/download?id=${file.id}&fileName=${file.saveName}" class="btn btn-sm btn-danger"><i class="fa fa-download"></i>下载</a>
                        </c:when>
                        <c:otherwise>
                            <div id="picker"><i class="fa fa-upload"></i> 选择文件</div>
                            <button id="addDir" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新建文件夹</button>
                        </c:otherwise>
                    </c:choose>

                    </div>
                </div>
                <div class="box-body no-padding">
                    <table id="fileTable" class="table table-hover">
                    <c:choose>
                    <c:when test="${not empty files}">

                            <c:forEach items="${files}" var="file">
                                <tr class="tr" rel="${file.id}">
                                    <c:if test="${file.type == 'dir'}">
                                        <td width="50" class="file_icon"><i class="fa fa-folder-o"></i></td>
                                    </c:if>
                                    <c:if test="${file.type == 'file'}">
                                        <td width="50" class="file_icon"><i class="fa fa-file-o"></i></td>
                                    </c:if>
                                    <td>${file.name}</td>
                                    <td><fmt:formatDate value="${file.updatetime}" pattern="MM月dd日"/></td>
                                    <td width="100"${file.size}></td>
                                    <td width="150">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <i class="fa fa-ellipsis-h"></i>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li rel="${file.id}" class="reName">重命名</li>
                                                <li rel="${file.id}" class="dele">删除</li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                         <c:when test="${file.type == 'file'}">
                            <a href="/file/download?id=${file.id}" class="btn btn-success">预览</a>
                            <tr>
                            <td width="50" class="file_icon"><i class="fa fa-file-o"></i></td>
                            <td>${file.name}</td>
                            <td><fmt:formatDate value="${file.updatetime}" pattern="MM月dd日"/></td>
                            <td width="100"${file.size}></td>
                            <td width="150">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <i class="fa fa-ellipsis-h"></i>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li rel="${file.id}" class="reName">重命名</li>
                                        <li rel="${file.id}" class="dele">删除</li>
                                    </ul>
                                </div>
                            </td>
                            </tr>
                        </c:when>
                         <c:otherwise>
                             暂无内容
                         </c:otherwise>
                    </c:choose>
                    </table>


                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

   <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/dist/js/art-template.js"></script>
<script src="/static/plugins/webUploader/webuploader.js"></script>
<script id="template" type="text/template">
    <tr class="tr" rel="{{id}}">
        <? if(type == 'file') {?>
        <td width="50" class="file_icon"><i class="fa fa-file-o"></i></td>
        <?} else if(type == 'dir') {?>
        <td width="50" class="file_icon"><i class="fa fa-folder-o"></i></td>
        <?}?>
        <td>{{name}}</td>
        <td>{{updatetime}}</td>
        <td width="100">{{size}}</td>
        <td width="100">
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-ellipsis-h"></i>
                </button>
                <ul class="dropdown-menu">
                    <li rel="{{id}}" class="reName">重命名</li>
                    <li rel="{{id}}" class="dele">删除</li>
                </ul>
            </div>
        </td>
    </tr>

</script>

<script>
    var pid = ${not empty requestScope.file ? requestScope.file.id : '0'}
    $(function () {
        $(".reName").click(function () {
            var id = $(this).attr("rel");

        });
        $(".dele").click(function () {
            var id = $(this).attr("rel");
            layer.confirm('你确定要删除这个文件吗?',function (index) {
                layer.close(index);
                $.get("/file/dele?id="+id).done(function (resp) {
                    if (resp.state == 'success'){
                        $("#fileTable").html("");
                        layer.msg("文件删除成功");
                        for (var i = 0;i < resp.data.length;i++){
                            var obj = resp.data.list[i];
                            obj.updatetime = moment(obj.updatetime).format("MM月DD日");
                            var html = template("template",obj);
                            $("#fileTable").append(html);
                        }
                    }else{
                        layer.msg("该文件下面尚有子文件，不能进行删除");
                    }
                }).error(function () {
                    layer.msg("服务器君生病了，请稍后再试");
                });
            });
        });
        $("#addDir").click(function () {
            layer.prompt({title:"请输入文件夹名称"},function (text,index) {
                layer.close(index);
                $.post("/file/new/folder",{"pid":pid,"name":text}).done(function (state) {
                    if (state.state == 'success'){
                        $("#fileTable").html("");
                        layer.msg("文件夹创建成功");
                        for (var i = 0;i < state.data.length;i++){
                            var obj = state.data.list[i];
                            obj.updatetime = moment(obj.updatetime).format("MM月DD日");
                            var html = template("template",obj);
                            $("#fileTable").append(html);
                        }
                    }else{
                        layer.msg(state.message);
                    }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            });
        });

    $(document).delegate(".tr","click",function () {
        var id = $(this).attr("rel");
        window.location.href = "/file?pid="+id;
    })

    //文件上传
    var uploader = WebUploader.create({
        pick:"#picker",
        swf:'/static/plugins/webUploader/Uploader.swf',
        server:'/file/upload',
        auto:true,
        fileVal:'file',
        formData:{
            "pid":pid,
        }
    });
    var loadIndex = -1;
    //开始上传
    uploader.on('uploadStart',function (file) {
            loadIndex = layer.load(2);
        })

    //上传成功
    uploader.on('uploadSuccess',function (file,resp) {
        if (resp.state == 'success'){
            layer.msg("文件上传成功");
            $("#fileTable").html("");
            for (var i = 0;i < resp.data.length;i++){
                var obj = resp.data[i];
                obj.updatetime = moment(obj.updatetime).format("MM月DD日");
                var html = template("template",obj);
                $("#fileTable").append(html);
            }
        }
    })
    //上传失败
    uploader.on('uploadError',function (file) {
        layer.msg("上传失败,服务器君生病了");
    });
    //无论上传失败还是成功
    uploader.on('uploadComplete',function (file) {
        layer.close(loadIndex);
    })
    });

</script>
</body>
</html>
