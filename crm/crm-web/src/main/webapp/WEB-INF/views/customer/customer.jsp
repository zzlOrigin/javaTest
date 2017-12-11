<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-我的客户</title>
   <%@include file="../include/css.jsp"%>
    <style>
        .td_title {
            font-weight: bold;
        }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

   <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="customer-my"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">客户资料</h3>
                    <div class="box-tools">
                        <a href="/customer/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                        <a href="/customer/edit/${customer.id}" class="btn bg-purple btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                        <button id="tranBtn" class="btn bg-orange btn-sm"><i class="fa fa-exchange"></i> 转交他人</button>
                        <button id="publicBtn" class="btn bg-maroon btn-sm"><i class="fa fa-recycle"></i> 放入公海</button>
                        <button id="deleBtn" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <c:if test="${not empty state}">
                    <div class="alert alert-danger">${state}</div>
                </c:if>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">姓名</td>
                            <td>${customer.custName}</td>
                            <td class="td_title">职位</td>
                            <td>${customer.jobTitle}</td>
                            <td class="td_title">联系电话</td>
                            <td>${customer.mobile}</td>
                        </tr>
                        <tr>
                            <td class="td_title">所属行业</td>
                            <td>${customer.industry}</td>
                            <td class="td_title">客户来源</td>
                            <td>${customer.source}</td>
                            <td class="td_title">级别</td>
                            <td>${customer.rank}</td>
                        </tr>
                        <tr>
                            <td class="td_title">地址</td>
                            <td colspan="5">${customer.address}</td>
                        </tr>
                        <tr>
                            <td class="td_title">备注</td>
                            <td colspan="5">${customer.remark}</td>
                        </tr>
                    </table>
                </div>
                <div class="box-footer">
                    <span style="color: #ccc" class="pull-right">创建日期：<fmt:formatDate value="${customer.setTime}"/> &nbsp;&nbsp;&nbsp;&nbsp;
                        最后修改日期：<fmt:formatDate value="${customer.updateTime}"/></span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                            <small><button id="chance" class="btn btn-success btn-xs"><i class="fa fa-plus"></i></button></small>
                        </div>
                        <div class="box-body">
                            <c:forEach items="${chances}" var="chance">
                            <p><a href="/recode/findOne/${chance.id}">${chance.name}</a></p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">日程安排
                            <small><button id="remind" class="btn btn-success btn-xs"><i class="fa fa-plus"></i></button></small>
                            </h3>
                            <ul>
                            <c:forEach items="${reminds}" var="remind">
                                <li>
                                <a href="/task/done/list"><i class="fa fa-user-o"></i>${remind.content}</a>
                                </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">相关资料</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <%--用户选择对话框（转交他人）--%>
    <div class="modal fade" id="chooseUserModel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">请选择转入账号</h4>
                </div>
                <div class="modal-body">
                    <select id="userSelect" class="form-control">
                        <c:forEach items="${staffs}" var="staff">
                            <c:if test="${staff.id != customer.staffId}">
                                <option value="${staff.id}">${staff.userName} (${staff.mobile})</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveTranBtn">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->



    <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        var customerId = ${customer.id}
            
         $("#remind").click(function () {
             window.location.href="/task/new/cust/"+customerId;
         });   
        $("#chance").click(function () {
            window.location.href="/recode/new/"+customerId;
        });
        $("#publicBtn").click(function () {
            layer.confirm("确定要将客户放到公海吗?",function (index) {
                layer.close(index);
                window.location.href = "/customer/my/"+customerId+"/public";
            })
        });
        $("#deleBtn").click(function () {
            layer.confirm("确定要将客户删除吗?",function (index) {
                layer.close(index);
                window.location.href = "/customer/dele/"+customerId;
            })
        });
        $("#tranBtn").click(function () {
            $("#chooseUserModel").modal({
                show:true,
                backdrop:'static'
            });
        });

        $("#saveTranBtn").click(function () {
            var toStaffId = $("#userSelect").val();
            var toStaffName = $("#userSelect option:selected").text();
            layer.confirm("确定要讲客户转交给"+toStaffName+"吗",function (index) {
                layer.close(index);
                window.location.href = "/customer/my/"+customerId+"/tran/"+toStaffId;
            })
        });

    })
</script>
</body>
</html>
s