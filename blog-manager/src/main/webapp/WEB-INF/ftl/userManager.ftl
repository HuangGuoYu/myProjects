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
    <title>用户管理</title>
</head>
<body>
<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display" style="text-align: center;">
    <thead>
    <tr>
        <th width="5%">用户编号</th>
        <th width="10%">博客名称</th>
        <th width="5%">账号/邮箱</th>
        <th width="10%">账户余额</th>
        <th width="15%">状态</th>
        <th width="20%">操作</th>
    </tr>
    </thead>
</table>
</body>
<script>
    var layer = layui.layer;
    var tables;
    var colunms = [
        {data:"id"},
        {data:"blogName"},
        {data:"account"},
        {data:"banlance",
            "render":function (data, type, row, meta) {
                if (data == undefined) {
                    return 0;
                }
                return data;
            }
        },
        {data:"state",
            "render":function (data, type, row, meta) {
                if (data == 0) {
                    return "不可用";
                } else if (data == 1) {
                    return "审核中";
                } else {
                    return "可用";
                }
            }
        },
        {
            "data":"state",
            "render":function (data, type, row, meta) {
                if (data == 0) {
                    return '<a href="javascript:void(0);" class="layui-btn layui-btn-normal" onclick="approval('+ row.id + ')">通过</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm layui-btn-disabled" onclick="forbidFun('+row.id+')">禁用</a>';
                } else {
                    return '<a href="javascript:void(0);" class="layui-btn layui-btn-normal layui-btn-disabled">通过</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="forbidFun('+row.id+')">禁用</a>';
                }
            }
        }
    ];
    tables = initTable("table_id_example", "/userManager/listData", "data", colunms);


    /**
     * 审核不通过
     */
    function forbidFun(uid) {
        layer.prompt({title: '请输入理由', formType: 2}, function(pass, index){
            $.post("/userManager/forbid",
                    {uid:uid,note:pass},
                    function (res) {
                        if (res.code == 200) {
                            layer.msg(res.data);
                            tables.ajax.reload();
                        } else {
                            layer.msg(res.msg);
                        }
                    }
            )
            layer.close(index);
        });
    }

    function approval(uid) {
        $.post("/userManager/approval",
                {uid:uid},
                function (res) {
                    if (res.code == 200) {
                        layer.msg(res.data);
                        tables.ajax.reload();
                    } else {
                        layer.msg(res.msg);
                    }
                }
        )
    }
</script>
</html>