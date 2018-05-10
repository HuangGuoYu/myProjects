<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui-nim/layui.all.js" type="text/javascript"></script>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="/resource/js/common-js.js"></script>
    <title>广告管理</title>
</head>
<body>
<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display" style="text-align: center;">
    <thead>
    <tr>
        <th width="5%">博客</th>
        <th width="10%">真实姓名</th>
        <th width="5%">支付宝账户</th>
        <th width="10%">申请时间</th>
        <th width="15%">金额</th>
        <th width="20%">状态</th>
        <th width="20%">操作</th>
    </tr>
    </thead>
</table>

</body>

<script>
    var layer = layui.layer;
    var tables;
    var colunms = [
        {data:"blog_name"},
        {data:"alipay_name"},
        {data:"alipay_account"},
        {data:"apply_time"},
        {data:"money"},
        {data:"state",
            "render":function (data, type, row, meta) {
                if (data == 1) {
                    return "提现中";
                } else if(data == 2) {
                    return "提现成功";
                } else {
                    return "提现失败";
                }
            }
        },
        {
            "data":"state",
            "render":function (data, type, row, meta) {
                if (data == 1) {
                    return '<a href="javascript:void(0);" class="layui-btn layui-btn-normal" onclick="withdrawSuccess('+ row.id + ')">成功提现</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm " onclick="withdrawError('+ row.id + ')">提现失败</a>';
                } else {
                    return '<a href="javascript:void(0);" class="layui-btn layui-btn-normal layui-btn-disabled">已处理</a>';
                }
            }
        }
    ];
    tables = initTable("table_id_example", "/userManager/userIncomeList", "data", colunms);

    function withdrawSuccess(id) {
        switchState(id, 2);
    }

    function withdrawError(id) {
        switchState(id, 3);
    }

    /**
     * 切换提现状态
     * @param id
     * @param state
     */
    function switchState(id, state) {
        $.post("/userManager/switchWithdrawState", {id:id,state:state}, function (res) {
            if (res.code == 200) {
                layer.msg(res.data);
                tables.ajax.reload();
            } else {
                layer.alert(res.msg);
            }
        })
    }

</script>
</html>