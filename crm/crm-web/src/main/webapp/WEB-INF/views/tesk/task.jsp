<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@include file="../include/header.jsp"%>

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="task-done"/>
    </jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">计划任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        <button class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</button>
                    </div>
                </div>
                <div class="box-body">
                   <ul class="todo-list" id="todoList">
                    <c:forEach items="${reminds.list}" var="remind">
                    <li class="${remind.done == 1 ? 'done' : ''}">
                        <input type="checkbox" class="task_checkbox" ${remind.done == 1 ? 'checked' : ''} value="${remind.id}">
                        <span class="text">${remind.content}</span>
                        <c:choose>
                        <c:when test="${not empty remind.chance}">
                            <a href="/recode/findOne/${remind.chance.id}"><i class="fa fa-money"></i> ${remind.chance.name}</a>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i>${remind.doneTime}</small>
                        </c:when>
                        <c:when test="${not empty remind.customer}">
                            <a href="/customer/find/${remind.customer.id}"><i class="fa fa-user-o"></i> ${remind.customer.custName}</a>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i>${remind.doneTime}</small>
                        </c:when>
                        <c:otherwise>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i>${remind.doneTime}</small>
                        </c:otherwise>
                        </c:choose>
                        <div class="tools">
                            <i class="fa fa-edit"></i>
                            <i class="fa fa-trash-o del_task" rel="${remind.id}"></i>
                        </div>
                    </li>
                    </c:forEach>
                    </ul>
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

<!-- jQuery 2.2.3 -->
<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/dist/js/art-template.js"></script>
<script type="text/template" id="template">
    <li class="{{done == 1 ? 'done' : ''}}">
    <input type="checkbox" class="task_checkbox" {{done == 1 ? 'checked' : ''}} value="{{id}}">
    <span class="text">{{content}}</span>
    <c:choose>
        <c:when test="{{not empty chance}}">
            <a href="/recode/findOne/{{chance.id}}"><i class="fa fa-money"></i> {{chance.name}}</a>
            <small class="label label-danger"><i class="fa fa-clock-o"></i>{{doneTime}}</small>
        </c:when>
        <c:when test="{{not empty customer}}">
            <a href="/customer/find/{{customer.id}}"><i class="fa fa-user-o"></i> {{customer.custName}}</a>
            <small class="label label-danger"><i class="fa fa-clock-o"></i>{{doneTime}}</small>
        </c:when>
        <c:otherwise>
            <small class="label label-danger"><i class="fa fa-clock-o"></i>{{doneTime}}</small>
        </c:otherwise>
    </c:choose>
    <div class="tools">
    <i class="fa fa-edit"></i>
    <i class="fa fa-trash-o del_task" rel="${id}"></i>
    </div>
    </li>
</script>
<script>
    $(function () {



        $(document).delegate(".task_checkbox","click",function () {
           var id = $(this).attr("value");
           $.get("/task/done",{"id":id}).done(function (date) {
                console.log(date.state);
                console.log(date.data.list[1]);
                console.log(date.data.size);
             if (date.state == 'success'){
                    $("#todoList").html("");
                    for (var i = 0; i < date.data.size;i++){
                        var obj = date.data.list[i];
                        var html = template("template",obj);
                        $("#todoList").append(html);
                    }
                }else {
                    layer.msg("服务器君生病了")
                }
           }).error(function () {
               layer.msg("服务器出错了");
           });
        });

        //删除
        $(".del_task").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗",function () {
                window.location.href = "/task/dele/"+id;
            })
        });
        
    });
</script>
</body>
</html>
