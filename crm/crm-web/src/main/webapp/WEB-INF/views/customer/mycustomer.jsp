<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>凯盛软件CRM-首页</title>
        <!-- Tell the browser to be responsive to screen width -->
       <%@include file="../include/css.jsp"%>
        <style>
            .name-avatar {
            display: inline-block;
            width: 50px;
            height: 50px;
            background-color: #ccc;
            border-radius: 50%;
            text-align: center;
            line-height: 50px;
            font-size: 24px;
            color: #FFF;
            }
            .table>tbody>tr:hover {
            cursor: pointer;
            }
            .table>tbody>tr>td {
            vertical-align: middle;
            }
            .star {
            font-size: 20px;
            color: #ff7400;
            }
            .pink {
                background-color:#f94877;
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

                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">我的客户</h3>
                            <div class="box-tools pull-right">
                                <a href="/customer/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增客户</a>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-file-excel-o"></i> 导出Excel <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="/customer/my/export.xls">导出为xls文件</a></li>
                                        <li><a href="/customer/my/export.csv">导出为csv文件</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="box-body no-padding">

                            <table class="table table-hover">
                                <tbody>
                                <c:if test="${not empty state}">
                                <div class="alert alert-danger">${state}</div>
                                </c:if>
                                <tr>
                                        <th width="80"></th>
                                        <th>姓名</th>
                                        <th>职位</th>
                                        <th>跟进时间</th>
                                        <th>级别</th>
                                        <th>联系方式</th>
                                    </tr>
                                    <c:if test="${empty customers.list}">
                                        <tr>
                                            <td colspan="6">你暂时还没有客户,请继续努力</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach items="${customers.list}" var="customer">
                                    <tr class="customers" rel="${customer.id}">
                                        <td><span class="name-avatar ${customer.sex == '女' ? 'pink' : ''}">${customer.custName.substring(0,1)}</span></td>
                                        <td>${customer.custName}</td>
                                        <td>${customer.jobTitle}</td>
                                        <td><fmt:formatDate value="${customer.followTime}"/></td>
                                        <td class="star">${customer.rank}</td>
                                        <td><i class="fa fa-phone"></i> ${customer.mobile} <br></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
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

            <%--添加账号模态框
            <!-- Modal -->
            <div class="modal fade" id="addCustormerModel" >
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">新增客户</h4>
                        </div>
                        <div class="modal-body">
                            <form id="addCustomerForm">
                                <div class="input-group margin-bottom-sm">
                                    <span class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></span>
                                    <input type="text" class="form-control" name="custName" placeholder="姓名">
                                </div>
                                <br>
                                <div class="input-group margin-bottom-sm">
                                    <span class="input-group-addon"><i class="fa  fa-briefcase fa-lg"></i></span>
                                    <input type="text" class="form-control" name="jobTitle" placeholder="职位">
                                </div>
                                <br>
                                <div class="input-group margin-bottom-sm">
                                    <span class="input-group-addon"><i class="fa  fa-users fa-lg"></i></span>
                                    <input type="text" class="form-control" name="trade" placeholder="行业">
                                </div>
                                <br>
                                <div class="input-group margin-bottom-sm">
                                    <span class="input-group-addon"><i class="fa fa-smile-o fa-lg"></i></span>
                                    <input type="text" class="form-control" name="source" placeholder="客户来源">
                                </div>
                                <br>
                                <div class="input-group margin-bottom-sm">
                                    <span class="input-group-addon"><i class="fa fa-phone-square fa-lg"></i></span>
                                    <input type="text" class="form-control" name="mobile" placeholder="手机号">
                                </div>
                                <br>
                                <div class="input-group margin-bottom-sm">
                                    <span class="input-group-addon"><i class="fa  fa-location-arrow fa-lg"></i></span>
                                    <input type="text" class="form-control" name="address" placeholder="地址">
                                </div>
                                <br>
                                <select name="level" class="form-control">
                                    <option value="">--选择星级--</option>
                                    <option value="★">★</option>
                                    <option value="★★">★★</option>
                                    <option value="★★★">★★★</option>
                                    <option value="★★★★">★★★★</option>
                                    <option value="★★★★★">★★★★★</option>
                                </select>
                                <br>
                                <div class="from-group">
                                    <textarea name="mark" id="editor"></textarea>
                                </div>
                                <br>


                                <input type="text" class="hidden" name="accountId" value="${sessionScope.curr_account.id}">

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="addCustomerFormBtn">保存</button>
                        </div>
                    </div>
                </div>
            </div>
            添加账号模态框结束--%>
        </div>
        <!-- ./wrapper -->
        <%@include file="../include/js.jsp"%>
    <script>
        $(function () {
            $(".customers").click(function () {
                var id = $(this).attr("rel");
                window.location.href = "/customer/find/"+id;
            });
        });
    </script>
    </body>
</html>
</html>