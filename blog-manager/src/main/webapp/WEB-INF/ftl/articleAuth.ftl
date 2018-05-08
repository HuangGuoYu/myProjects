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
    <title>文章审核</title>
</head>
<body>
<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display" style="text-align: center;">
    <thead>
    <tr>
        <th width="5%">文章标识</th>
        <th width="10%">文章名称</th>
        <th width="5%">作者标识</th>
        <th width="10%">作者</th>
        <#--<th>内容</th>-->
        <th width="5%">是否原创</th>
        <th width="15%">状态</th>
        <th width="20%">操作</th>
    </tr>
    </thead>
</table>
</body>
<script>
    var layer = layui.layer;
    var tables;
</script>
<script>
        var content = {};
       var colunms = [
        {"data":"id"},
        {"data":"title"},
        {"data":"user_id"},
        {"data":"blog_name"},
//        {"data":"content",
//            "render":function (data, type, row, meta) {
//                return "<span onclick='seeTheContent("+ row.content +")'>点击查看</span>";
//            }
//        },
        {"data":"is_original",
         "render":function (data, type, row, meta) {
            if (data == 1) {
                return "<a href='javascript:void(0)' class='layui-btn layui-btn-normal'>原创</a>"
            } else {
                return "<a href='javascript:void(0)' class='layui-btn layui-btn-warm'>转载</a>"
            }
         }
        },
        {
            "data":"state",
            "render":function (data, type, row, meta) {
                if (data == 0) {
                    return "未审核";
                } else if (data == 1) {
                    return "审核通过";
                } else {
                    return "未通过";
                }
            }
        },
        {
            "data":"state",
            "render":function (data, type, row, meta) {
                if (data == 0) {
                    content["_" + row.id] = row.content;
                    return '<a href="javascript:void(0);" class="layui-btn" onclick="seeTheContent(\''+ row.id +'\')">查看内容</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-normal" onclick="approval('+ row.id + ',' + row.user_id+ ')">通过</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="forbidFun('+row.id+')">禁用</a>';
                } else {
                    return '<a href="javascript:void(0);" class="layui-btn" onclick="seeTheContent(\''+ row.content +'\')">查看内容</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-normal layui-btn-disabled">通过</a>' +
                            '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="forbidFun('+row.id+')">禁用</a>';
                }
            }
        }
    ];
    tables = initTable("table_id_example", "/article/list", "data", colunms);

    function seeTheContent(id) {
        layer.open({
            type: 1,
            title:"博客内容",
            skin: 'layui-layer-rim', //加上边框
            area: ['600px', '500px'], //宽高
            content: content["_" + id]
        });
    }

    function approval(aid, uid) {
        $.post("/article/approval",
                {aid:aid,uid:uid},
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

       /**
        * 审核不通过
        */
    function forbidFun(aid) {
        layer.prompt({title: '请输入理由', formType: 2}, function(pass, index){
            $.post("/article/forbid",
                    {aid:aid,note:pass},
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
</script>
</html>