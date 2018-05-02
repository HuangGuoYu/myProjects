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
    <title>评论管理</title>
</head>
<body>
<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display" style="text-align: center;">
    <thead>
    <tr>
        <th width="5%">用户编号</th>
        <th width="10%">博客名称</th>
        <th width="5%">账号/邮箱</th>
        <th width="30%">评论内容</th>
        <th width="20%">操作</th>
    </tr>
    </thead>
</table>
</body>
<script>
    var layer = layui.layer;
    var tables;
    var colunms = [
        {data:"cuser_id"},
        {data:"blog_name"},
        {data:"account"},
        {data:"content"},
        {
            "data":"state",
            "render":function (data, type, row, meta) {
                return '<a href="javascript:void(0);" class="layui-btn" onclick="delFun('+row.id +')">删除记录</a>'+
                '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="forbidFun('+row.cuser_id +')">禁用账户</a>';
            }
        }
    ];
    tables = initTable("table_id_example", "/userManager/commentList", "data", colunms);


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

    function delFun(id) {
        //询问框
        layer.confirm('确定删除该条记录？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.post("/userManager/delComment", {id:id}, function (res) {
                if (res.code == 200) {
                    layer.msg(res.data);
                    tables.ajax.reload();
                } else {
                    layer.msg(res.msg);
                }
            });
        }, function(){

        });
    }

</script>
</html>