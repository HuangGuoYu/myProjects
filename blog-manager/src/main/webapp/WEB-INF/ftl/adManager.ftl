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
        <th width="5%">广告编号</th>
        <th width="10%">目标地址</th>
        <th width="5%">图片地址</th>
        <th width="10%">描述</th>
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
        {data:"url"},
        {data:"imageUrl"},
        {data:"content"},
        {data:"state",
            "render":function (data, type, row, meta) {
                if (data == 1) {
                    return "已上线";
                } else if (data == 0) {
                    return "已下架";
                }
            }
        },
        {
            "data":"state",
            "render":function (data, type, row, meta) {
                if (data == 0) {
                    return '<a href="javascript:void(0);" class="layui-btn layui-btn-normal" onclick="approval('+ row.id + ')">上线</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm layui-btn-disabled">下架</a>';
                } else {
                    return '<a href="javascript:void(0);" class="layui-btn layui-btn-normal layui-btn-disabled">上线</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="forbidFun('+row.id+')">下架</a>';
                }
            }
        }
    ];
    tables = initTable("table_id_example", "/file/adList", "data", colunms);

    function approval(id) {
        $.post("/file/adApproval",
                {id:id},
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

    function forbidFun(id) {
        $.post("/file/adForbid",
                {id:id},
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