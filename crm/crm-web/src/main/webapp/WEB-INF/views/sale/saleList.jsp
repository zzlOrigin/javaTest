<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
   
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

   <%@include file="../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sale-my"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <a href="/recode/add" type="button" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 添加机会
                        </a>
                    </div>
                </div>
                <c:if test="${not empty state}">
                    <div class="alert alert-danger">${state}</div>
                </c:if>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>机会名称</th>
                            <th>关联客户</th>
                            <th>机会价值</th>
                            <th>当前进度</th>
                            <th>最后跟进时间</th>
                            <th>
                                #
                            </th>
                        </tr>

                        <c:if test="${empty pages.list}">
                            你还没有机会，
                        </c:if>
                        <c:forEach items="${pages.list}" var="change">
                        <tr class="saleList" rel="${change.id}">
                            <td>${change.name}</td>
                            <td>${change.customer.custName}</td>
                            <td>${change.value}</td>
                            <td>${change.progress}</td>
                            <td><fmt:formatDate value="${change.follow}"/></td>
                            <td>
                                #
                            </td>
                        </tr>
                        </c:forEach>
                        </thead>
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
<script>
    $(function () {
        $(".saleList").click(function () {
            var id = $(this).attr("rel");
            window.location.href = "/recode/findOne/"+id;
        });
    });
</script>
</body>
</html>
