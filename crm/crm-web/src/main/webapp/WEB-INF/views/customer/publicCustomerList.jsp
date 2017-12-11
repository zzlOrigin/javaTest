<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>凯盛软件CRM-公海客户</title>
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
                <jsp:param name="menu" value="customer-public"/>
            </jsp:include>
            <!-- 右侧内容部分 -->
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">

                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">公海客户</h3>
                            <div class="box-tools pull-right">
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
                                <c:if test="${empty pages.list}">
                                <div class="alert alert-danger">没有公海客户</div>
                                </c:if>
                                <tr>
                                        <th width="80"></th>
                                        <th>姓名</th>
                                        <th>职位</th>
                                        <th>跟进时间</th>
                                        <th>级别</th>
                                        <th>联系方式</th>
                                    </tr>

                                    <c:forEach items="${pages.list}" var="customer">
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

        </div>
        <!-- ./wrapper -->
        <%@include file="../include/js.jsp"%>
    <script>
        $(function () {
            $(".customers").click(function () {
                var id = $(this).attr("rel");
                window.location.href = "/customer/public/find/"+id;
            });
        });
    </script>
    </body>
</html>
</html>