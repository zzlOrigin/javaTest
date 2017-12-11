<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM | 账号管理</title>
    <%@include file="../include/css.jsp" %>
    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">

    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp" %>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="employee"/>
    </jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-2">
                    <div class="box">
                        <div class="box-body">
                            <button id="addDept" class="btn btn-default">添加部门</button>
                            <button id="deleDepe" class="btn btn-danger btn-sm">删除部门</button>
                            <input type="hidden" id="deptId">
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">员工管理</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" id="addStaff" title="Collapse">
                                    <i class="fa fa-plus"></i> 添加员工
                                </button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table" id="dataTable">
                                <thead>
                                <tr>
                                    <th hidden>id</th>
                                    <th>姓名</th>
                                    <th>部门</th>
                                    <th>手机</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <%--添加账号模态框--%>
    <!-- Modal -->
    <div class="modal fade" id="addEmployeeModel" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加账号</h4>
                </div>
                <div class="modal-body">
                    <form id="addEmployeeForm">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="userName">
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <input type="text" class="form-control" name="mobile">
                        </div>
                        <div class="form-group">
                            <label>密码(默认000000)</label>
                            <input type="password" class="form-control" name="password" id="password" value="000000">
                        </div>
                        <div class="form-group">
                            <label>验证密码(默认000000)</label>
                            <input type="password" class="form-control" name="password2" value="000000">
                        </div>
                        <div class="form-group">
                            <label>所属部门</label>
                            <div id="checkboxList"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="addEmployeeFormBtn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>Copyright &copy; 2010 -2017 <a href="http://almsaeedstudio.com">凯盛软件</a>.</strong> All rights
        reserved.
    </footer>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp" %>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {
//添加员工
        $("#addStaff").click(function () {
            $("#checkboxList").html("");
            $.get("employee/dept.json").done(function (data) {
                for(var i = 0; i < data.length;i++){
                    var obj = data[i];

                    if (obj.id != 1){
                        var html = '<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+obj.id+'">'+obj.departName+'</label>';
                        $("#checkboxList").append(html);
                    }
                }
               $("#addEmployeeModel").modal({
                   show:true,
                   backdrop:'static'
               });
            }).error(function () {
                layer.msg("获取部门信息失败");
            });

        });

//验证输入信息是否正确
        $("#addEmployeeFormBtn").click(function () {
            $("#addEmployeeForm").submit();
        });
        $("#addEmployeeForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                userName:{
                    required:true
                },
                mobile:{
                    required:true
                },
                deptId:{
                    required:true
                },
                password:{
                    required:true
                },
                password2:{
                    required:true,
                    equalTo:"#password"
                }
            },
            messages:{
                userName:{
                    required:"请输入账号"
                },
                mobile:{
                    required:"请输入电话号码"
                },
                deptId:{
                    required:"请选择部门"
                },
                password:{
                    required:"请输入密码"
                },
                password2:{
                    required:"请再次输入密码",
                    equalTo:"两次密码输入不一致"
                }
            },
            //from表单提交之后
            submitHandler:function() {
                $.post("/employee/new",$("#addEmployeeForm").serialize()).done(function (data) {
                   if (data.state == 'success'){
                       $("#addEmployeeModel").modal('hide');
                       dataTable.ajax.reload();
                   }else {
                       layer.msg(data.message);
                   }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            }
        });

//删除账号
        $(document).delegate(".delEmployeeLink","click",function () {
           var id = $(this).attr("rel");
           layer.confirm("确定要删除吗",function (index) {
               layer.close(index);
               $.post("/employee/"+id+"/delete").done(function (data) {
                   if(data.state = "success"){
                       dataTable.ajax.reload();
                   }else {
                       layer.msg(data.message);
                   }
               }).error(function () {
                   layer.msg("系统异常，删除失败");
               });
           });
        });



//table
        var dataTable = $("#dataTable").DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "/employee/load.json",
                "data": function (data) {
                    data.deptId = $("#deptId").val();
                }
            },
            "lengthChange": false,
            "pageLength": 10,
            "columns": [
                {"data": "id"},
                {"data": "userName"},
                {"data":function(row){
                    var deptArray = row.departments;
                    var str = "";
                    for(var i = 0;i < deptArray.length;i++) {
                        str += deptArray[i].departName + " ";
                    }
                    return str;
                }},
                {"data": "mobile"},
                {
                    "data": function (row) {
                        return "<a href='javascript:;' rel='" + row.id + "' class='delEmployeeLink'>删除</a>";
                    }
                }
            ],
            "columnDefs": [
                {
                    "targets": [2, 3, 4],
                    "orderable": false
                },
                {
                    "targets": [0],
                    "visible": false
                }
            ],
            language: {
                "search": "账号:",
                "info": "显示从 _START_ 到 _END_ 条数据，共 _TOTAL_ 条",
                "infoEmpty": "没有任何数据",
                "emptyTable": "暂无数据",
                "processing": "加载中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        $("#deleDepe").attr("disabled","disabled");

        //删除部门
        $("#deleDepe").click(function () {
            $.post("/dept/dele",{"id":$("#deptId").val()}).done(function (data) {
                if (data.state == "success"){
                    layer.msg("删除部门成功");
                     var treeObj = $.fn.zTree.getZTreeObj("ztree");
                    treeObj.reAsyncChildNodes(null, "refresh");
                    $("#deptId").val(1);
                    dataTable.ajax.reload();
                }else {
                    layer.msg(data.mess)
                }
            }).error(function () {
                layer.msg("系统异常，删除部门失败")
            });
        });


//zTree
        $("#addDept").click(function () {
            layer.prompt({title: "请输入部门名称"}, function (text, index) {
                layer.close(index);
                $.post("/employee/dept/new", {"deptName": text}).done(function (data) {
                    if (data.state == "success") {
                        layer.msg("添加部门成功");
                        var treeObj = $.fn.zTree.getZTreeObj("ztree");
                        treeObj.reAsyncChildNodes(null, "refresh");
                    } else {
                        layer.msg(data.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙,请稍候");
                });
            });
        });

        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "pid"
                },
                key: {
                    name: "departName"
                }
            },
            async: {
                enable: true,
                url: "/employee/dept.json",
                type: "get",
                dataFilter: ajaxDataFilter
            },
            callback: {

                onClick: function (event, treeId, treeNode, clickFlag) {
                    if(treeNode.id == null || treeNode.id == 1){
                        $("#deleDepe").attr("disabled","disabled");
                    }else{
                        $("#deleDepe").removeAttr("disabled");
                    }
                    $("#deptId").val(treeNode.id);
                    dataTable.ajax.reload();
                }
            }
        };

        function ajaxDataFilter(treeId, parentNode, responseData) {
            if (responseData) {
                for (var i = 0; i < responseData.length; i++) {
                    if (responseData[i].id == 1) {
                        responseData[i].open = true;
                        break;
                    }
                }
            }
            return responseData;
        }

        /* var zNodes =[
             { id:1, pId:0, name:"凯盛软件", open:true},
             { id:11, pId:1, name:"开发部"},
             { id:111111, pId:11, name:"华北开发部"},
             { id:111, pId:1, name:"销售部"},
             { id:112, pId:1, name:"经理办公室"}
         ];*/
        $.fn.zTree.init($("#ztree"), setting);
    });
</script>
</body>
</html>
