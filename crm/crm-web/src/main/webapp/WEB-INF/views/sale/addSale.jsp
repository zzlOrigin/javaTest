<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <%@include file="../include/header.jsp"%>

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sale-my"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 添加机会
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <form id="saveForm" method="post">
                        <div class="form-group">
                            <label>机会名称</label>
                            <input type="text" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <label>关联客户</label>
                            <select name="id" class="form-control">
                                <c:if test="${not empty customer}">
                                    <option value="${customer.id}">${customer.custName}</option>
                                </c:if>
                                <c:if test="${empty custome}">
                                <c:forEach items="${customers}" var="customer">
                                <option value="${customer.id}">${customer.custName}</option>
                                </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>机会价值</label>
                            <input type="text" class="form-control" name="value">
                        </div>
                        <div class="form-group">
                            <label>当前进度</label>
                            <select name="progress" class="form-control">
                                <option value="初访">初访</option>
                                <option value="意向">意向</option>
                                <option value="报价">报价</option>
                                <option value="成交">成交</option>
                                <option value="暂时搁置">暂时搁置</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>详细内容</label>
                            <textarea name="content" class="form-control"></textarea>
                        </div>
                    </form>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button id="save" class="btn btn-primary">保存</button>
                </div>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

   <%@include file="../include/js.jsp"%>
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>

<script>
    $(function () {
        $("#save").click(function () {
            $("#saveForm").submit();
        });
    });
</script>
</body>
</html>
