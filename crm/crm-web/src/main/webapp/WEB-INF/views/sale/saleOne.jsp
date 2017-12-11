<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-销售机会详情</title>
    <!-- Tell the browser to be responsive to screen width -->
   <%@include file="../include/css.jsp"%>
    <![endif]-->
    <style>
        .td_title {
            font-weight: bold;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
    </style>
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
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">销售机会基本资料</h3>
                    <div class="box-tools">
                        <a href="/sales/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                        <button id="delBtn" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">机会名称</td>
                            <td>${chance.name}</td>
                            <td class="td_title">价值</td>
                            <td>${chance.value}</td>
                            <td class="td_title">当前进度</td>
                            <td>
                                ${chance.progress}
                                <button class="btn btn-xs btn-success" id="showChangeProgressModalBtn"><i class="fa fa-pencil"></i></button>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="box-footer">
                    <span style="color: #ccc" class="pull-right">
                        创建日期：<fmt:formatDate value="${chance.creation}" pattern="yyyy-MM-dd hh:mm"/>
                    </span>
                </div>
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">关联客户资料</h3>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">姓名</td>
                            <td>${chance.customer.custName}</td>
                            <td class="td_title">职位</td>
                            <td>${chance.customer.jobTitle}</td>
                            <td class="td_title">联系电话</td>
                            <td>${chance.customer.mobile}</td>
                        </tr>
                        <tr>
                            <td class="td_title">所属行业</td>
                            <td>${chance.customer.industry}</td>
                            <td class="td_title">客户来源</td>
                            <td>${chance.customer.source}</td>
                            <td class="td_title">级别</td>
                            <td class="star">${chance.customer.rank}</td>
                        </tr>

                        <tr>
                            <td class="td_title">地址</td>
                            <td colspan="5">${chance.customer.address}</td>
                        </tr>


                        <tr>
                            <td class="td_title">备注</td>
                            <td colspan="5">${chance.customer.remark }</td>
                        </tr>

                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <h4>跟进记录
                        <small><button id="showRecordModalBtn" class="btn btn-success btn-xs"><i class="fa fa-plus"></i></button></small>

                    </h4>
                    <ul class="timeline">
                        <c:forEach items="${records}" var="record">
                            <c:choose>
                            <c:when test="${record.context == '暂无跟进记录'}">
                            <li>
                                <!-- timeline icon -->
                                <i class="fa fa-circle-o bg-red"></i>
                                <div class="timeline-item">
                                    <div class="timeline-body">
                                        暂无跟进记录
                                    </div>
                                </div>
                            </li>
                            </c:when>
                            <c:when test="${record.context == '将当前进度修改为 [成交]'}">
                            <li>
                                <!-- timeline icon -->
                                <i class="fa fa-check bg-green"></i>
                                <div class="timeline-item">
                                    <span class="time"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${record.setup}"/></span>
                                    <div class="timeline-body">
                                        将当前进度修改为 [成交]
                                    </div>
                                </div>
                            </li>
                            </c:when>
                            <c:when test="${record.context == '将当前进度修改为 [暂时搁置]'}">
                            <li>
                                <!-- timeline icon -->
                                <i class="fa fa-close bg-red"></i>
                                <div class="timeline-item">
                                    <span class="time"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${record.setup}"/></span>
                                    <div class="timeline-body">
                                        将当前进度修改为 [暂时搁置]
                                    </div>
                                </div>
                            </li>
                            </c:when>
                            <c:otherwise>
                            <li>
                                <!-- timeline icon -->
                                <i class="fa fa-info bg-blue"></i>
                                <div class="timeline-item">
                                    <span class="time"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${record.setup}"/></span>
                                    <div class="timeline-body">
                                        ${record.context}
                                    </div>
                                </div>
                            </li>
                            </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">日程安排
                            <small><button id="remind" class="btn btn-success btn-xs"><i class="fa fa-plus"></i></button></small>
                            </h3>
                            <c:forEach items="${reminds}" var="remind">
                                <ul>
                                    <c:forEach items="${reminds}" var="remind">
                                        <li>
                                            <a href="/task/done/list"><i class="fa fa-user-o"></i>${remind.content}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:forEach>
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

            <div class="modal fade" id="recordModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">添加跟进记录</h4>
                        </div>
                        <div class="modal-body">
                            <form action="/recode/my/new/record" id="recordForm" method="post">
                                <input type="hidden" name="saleId" value="${chance.id}">
                                <textarea id="recordContent"  class="form-control" name="content"></textarea>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="saveRecordBtn">保存</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->


            <div class="modal fade" id="changeProgressModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">更新当前进度</h4>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="/recode/my/chance/update" id="updateProgressForm">
                                <input type="hidden" name="id" value="${chance.id}">
                                <select name="progress" class="form-control">
                                    <c:forEach items="${sales}" var="sale">
                                        <option value="${sale}">${sale}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="saveProgress">更新</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->



</div>
<!-- ./wrapper -->
<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {
        var chanceId = ${chance.id}

        $("#remind").click(function () {
            window.location.href="/task/new/chance/"+chanceId;
        });
        $("#showRecordModalBtn").click(function () {
            $("#recordModal").modal();
        });

        $("#saveRecordBtn").click(function () {
            $("#recordForm").submit();
        });

        $("#recordForm").validate({
            errorClass : "text-danger",
            errrorElement : "span",
            rules : {
                content : {
                    required : true
                }
            },
            messages : {
                content : {
                    required : "请输入要添加的跟进记录"
                }
            }
        })
        $("#showChangeProgressModalBtn").click(function () {
            $("#changeProgressModal").modal();
        });
        
        $("#saveProgress").click(function () {
           $("#updateProgressForm").submit();
        });
        
        $("#delBtn").click(function () {
            var id = ${chance.id}
            window.location.href = "/recode/dele/"+id;
        });
    });
</script>
</body>
</html>
