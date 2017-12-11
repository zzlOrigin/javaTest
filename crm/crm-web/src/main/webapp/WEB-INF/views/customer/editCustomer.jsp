<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-修改客户</title>
    <%@include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <!-- 左侧菜单栏 -->
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="customer-my"/>
    </jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改客户</h3>
                    <div class="box-tools pull-right">
                        <a href="/customer/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form id="editForm" action="/customer/edit" method="post">
                        <input type="hidden" name="id" value="${customer.id}">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="custName" value="${customer.custName}">
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <div>
                                <label class="redio-inline">
                                    <input type="radio" name="sex" value="男" ${customer.sex == '男' ? 'checked' : ''}>先生
                                </label>
                                <label class="redio-inline">
                                    <input type="radio" name="sex" value="女" ${customer.sex == '女' ? 'checked' : ''}>女士
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>职位</label>
                            <input type="text" class="form-control" name="jobTitle" value="${customer.jobTitle}">
                        </div>
                        <div class="form-group">
                            <label>联系方式</label>
                            <input type="text" class="form-control" name="mobile" value="${customer.mobile}">
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input type="text" class="form-control" name="address" value="${customer.address}">
                        </div>
                        <div class="form-group">
                            <label>所属行业</label>
                            <select class="form-control" name="industry">
                                <option ${customer.industry == '' ? 'selected' : ''} value=""></option>
                                <option ${customer.industry == '互联网' ? 'selected' : ''} value="互联网">互联网</option>
                                <option ${customer.industry == '电力能源' ? 'selected' : ''} value="电力能源">电力能源</option>
                                <option ${customer.industry == '其他' ? 'selected' : ''} value="其他">其他</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>客户来源</label>
                            <select class="form-control" name="source">
                                <option ${customer.source == '' ? 'selected' : ''} value=""></option>
                                <option ${customer.source == 'DM广告' ? 'selected' : ''} value="DM广告">DM广告</option>
                                <option ${customer.source == '网络媒体' ? 'selected' : ''} value="网络媒体">网络媒体</option>
                                <option ${customer.source == '顾客推荐' ? 'selected' : ''} value="顾客推荐">顾客推荐</option>
                                <option ${customer.source == '主动上门' ? 'selected' : ''} value="主动上门">主动上门</option>
                                <option ${customer.source == '其他' ? 'selected' : ''} value="其他">其他</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>级别</label>
                            <select class="form-control" name="rank">
                                <option ${customer.rank == '' ? 'selected' : ''} value=""></option>
                                <option ${customer.rank == '★' ? 'selected' : ''} value="★">★</option>
                                <option ${customer.rank == '★★' ? 'selected' : ''} value="★★">★★</option>
                                <option ${customer.rank == '★★★' ? 'selected' : ''} value="★★★">★★★</option>
                                <option ${customer.rank == '★★★★' ? 'selected' : ''} value="★★★★">★★★★</option>
                                <option ${customer.rank == '★★★★★' ? 'selected' : ''} value="★★★★★">★★★★★</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <input type="text" class="form-control" name="remark" value="${customer.remark}">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button id="edit" class="btn btn-primary">修改</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <%@include file="../include/footer.jsp" %>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp" %>
<script>
    $(function () {
        $("#edit").click(function () {
            $("#editForm").submit();
        });
    });
</script>
</body>
</html>
