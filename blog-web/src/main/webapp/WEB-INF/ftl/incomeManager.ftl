<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui-1/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/writeAricle.css">
    <link rel="stylesheet" href="/resource/css/cateManager.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui-1/layui.js" type="text/javascript"></script>

    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="/resource/js/echarts.js"></script>
    <style>
        .regular p{
            font-weight: bold;
            padding: 5px;
        }

        .regular span {
            display: inline-block;
            font-weight: 100;
            margin-top: 10px;
            text-align: right;
            float: right;
        }
    </style>
</head>

<body style="background-color: #f4f4f4">
<div class="header">
    <!-- 顶部 -->
    <div class="header-bar public-container clearfloat">
        <div class="info_bar left">
            <a href="/user/userIndex"><img src="${uinfo.headIcon}" rel="wu" style="border-radius: 50%;display: inline-block;height: 71px; width: 71px;"></a>
            <span class="blog-name" style="">${data.blogName}</span>
        </div>
        <a class="exit right" style="cursor:pointer;" href="/user/logout">
            退出
        </a>
    </div>
</div>

<div class="content clearfloat">

    <div class="public-container">
        <div class="nav_menu left">
            <div class="list-group-item list-group-title">内容</div>
            <div class="list-group">
                <a href="/article/writeArticle" class="list-group-item" data-title="article" target="_self">发布文章</a>
                <a href="/article/cateManager"  class="list-group-item none" target="_self">分类管理</a>
                <a href="/article/articleManager" class="list-group-item none" data-title="comment" target="_self">文章管理</a>
                <a href="/user/userCenter" class="list-group-item none">个人中心</a>
                <a href="/income/index" class="list-group-item none  active">收益管理</a>
            </div>
        </div>

        <div class="menu_content right">
            <div>
                <h1 style="border-left: 3px solid red;"><strong>&nbsp;&nbsp;&nbsp;收益规则:</strong></h1>
                <div style="padding: 10px;padding-left: 30px;" class="regular clearfloat">
                    <p>1.所写文章访问量达到1000账户余额增加3元</p>
                    <p>2.当前账户关注量没增加50账户余额增加1元</p>
                    <p>3.原创文章审核通过每10篇账户余额正价1元</p>
                    <span>注意：账户余额达50元可提现</span>
                </div>
            </div>
            <hr />

            <h1 style="border-left: 3px solid red;"><strong>&nbsp;&nbsp;&nbsp;近一周收益统计(单位：元)</strong></h1>
            <div id="main" style="width: 600px;height:400px;"></div>
            <hr />

            <h1 style="border-left: 3px solid red;">
                <strong>&nbsp;&nbsp;&nbsp;当前余额：</strong>
                <strong style="font-size: 30px; color: red;">${data.banlance} &nbsp;元</strong>
            </h1>
            <hr>
            <br />
            <h1 style="border-left: 3px solid red;">
                <strong>&nbsp;&nbsp;&nbsp;申请提现</strong>
            </h1>
            <br />
            <div class="style_hui_12" style="width:100%; margin:auto; text-align:left; background-color:#dedede; padding-left:15px; line-height:40px;">
                提示：请自行核对支付宝账号，并确定账户真实名与填写的一直，账号错误自行负责！<br>
            </div>
            <br>
            <div class="layui-form-item">
                <label class="layui-form-label">提现金额</label>
                <div class="layui-input-block">
                    <input type="number" name="money" required  lay-verify="required" placeholder="请输入提现金额（单位元）" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">账户真实名</label>
                <div class="layui-input-block">
                    <input type="text" name="alipayName" required  lay-verify="required" placeholder="请输入支付宝真实名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">支付宝账号</label>
                <div class="layui-input-block">
                    <input type="text" name="alipayAccount" required  lay-verify="required" placeholder="请输入支付宝账号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" onclick="withdraw()">立即提交</button>
                </div>
            </div>
            <br />
            <h1 style="border-left: 3px solid red;">
                <strong>&nbsp;&nbsp;&nbsp;提现记录</strong>
            </h1>
            <br/>
            <table id="table_id_example" class="display" style="text-align: center;">
                <thead>
                <tr>
                    <th>申请时间</th>
                    <th>账户姓名</th>
                    <th>支付宝账号</th>
                    <th>金额（单位：元）</th>
                    <th>状态</th>
                </tr>
                </thead>
            </table>
        </div>
        </div>
    </div>
</div>


</body>
<script src="/resource/editor/wangEditor.min.js"></script>
<script type="text/javascript">
    var layer;
    var tables;
    layui.use('layer', function(){
        layer = layui.layer;
    });
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var line_option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };

    $.get("/income/chart",function (res) {
        if (res.code == 200) {
            line_option.xAxis.data = res.data.xs.reverse();
            line_option.series[0].data = res.data.xds.reverse();
            console.log(line_option);
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(line_option);
        }
    });



    function withdraw() {
        var data = {};
        var reg = /^[1-9]\d*$/;
        $("input").each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        console.log("ok");
        if (!reg.test(data['money'])) {
            layer.alert("提现金额不能为小数");
            return;
        }
        if (data['money'] < 50) {
            layer.alert("提现金额不能低于50");
            return;
        }
        $.post("/income/withdraw", data, function (res) {
            if (res.code == 200) {
                layer.msg(res.data);
                tables.ajax.reload();
            } else {
                layer.msg(res.msg);
            }
        });
    }
    var tables;
    <!--第三步：初始化Datatables-->
    $(document).ready( function () {
        tables = $('#table_id_example').DataTable({
            ajax:{
                url:"/income/withdrawRecord",
                dataSrc:"data"
            },
            columns:[
                {data:"applyTime"},
                {data:"alipayName"},
//                {data:"ord"},
                {data:"alipayAccount"},
                {data:"money"},
                {data:"state",
                    "render":function (data, type, row, meta) {
                        if (data == 1) {
                            return "提现中";
                        } else if (data == 2) {
                            return "提现成功"
                        } else {
                            return "提现失败";
                        }
                    }
                    }
            ],
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
    } );
</script>
</html>