<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-静态数据演示</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="show-static"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">每月新增客户的数量</h3>
                </div>
                <div class="box-body">
                    <div id="custNum" style="height: 300px;width: 100%"></div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">销售机会漏斗图</h3>
                        </div>
                        <div class="box-body">
                            <div id="chance" style="height: 300px;width: 100%"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">业绩增长表</h3>
                        </div>
                        <div class="box-body">
                            <div id="sale" style="height: 300px;width: 100%"></div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/echarts/echarts.min.js"></script>
<script>
    $(function () {
        var custNum = echarts.init(document.getElementById("custNum"));
        var chance = echarts.init(document.getElementById('chance'));
        var sale = echarts.init(document.getElementById('sale'));



        $.get('/customer/staticCustNum',function (resp) {
            if (resp.state == 'success'){
                var datas = [];
                var nameDatas = [];
                for (var i = 0;i < resp.data.length;i++){
                    var obj = resp.data[i];
                    datas.push(obj.month + "月");
                    nameDatas.push(obj.number)
                }
                var option = {
                    title : {
                        text: '各月的客户人数'
                    },
                    legend:{
                        data:['人数']
                    },
                    xAxis:{
                        data:datas
                    },
                    yAxis:{},
                    series:[{
                        name:'人数',
                        type:'bar',
                        data:nameDatas
                    }]
                }

                var option1 = {
                    title : {
                        text: '每月新增客户',
                        subtext: '折线图'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['最高新增','最低新增']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : false,
                            data : datas
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            axisLabel : {
                                formatter: '{value}'
                            }
                        }
                    ],
                    series : [
                        {
                            name:'最高新增人数',
                            type:'line',
                            data:nameDatas,
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                            markLine : {
                                data : [
                                    {type : 'average', name: '平均值'}
                                ]
                            }
                        },


                    ]
                };

            }
            custNum.setOption(option);
            sale.setOption(option1);
        });
        $.get('/recode/staticFunnel',function (resp) {
            chance.setOption( {
                    title : {
                        text: '销售机会漏斗图',
                        subtext: '百度Echarts'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    legend: {
                        data : ['展现','点击','访问','咨询','订单']
                    },
                    calculable : true,
                    series : [
                        {
                            name:'漏斗图',
                            type:'funnel',
                            width: '60%',
                            data:resp.data

                        }
                    ]});



        })


    });


</script>

</body>
</html>
