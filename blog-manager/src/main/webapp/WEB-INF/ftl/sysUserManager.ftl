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
    <title>系统用户管理</title>
</head>
<body>
<span onclick="addUser()" class="layui-btn">添加用户</span>
<hr />
<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display" style="text-align: center;">
    <thead>
    <tr>
        <th width="5%">用户账号</th>
        <th width="10%">用户姓名</th>
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
        {data:"name"},
        {
            "data":"id",
            "render":function (data, type, row, meta) {
                return '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="editUser('+ data +')">修改密码</a>' +
                '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="delUser('+ data +')">删除用户</a>';
            }
        }
    ];
    tables = initTable("table_id_example", "/sysUser/list", "data", colunms);

    function editUser(id) {
            layer.prompt({title: '请输入新密码', formType: 1}, function(pass, index){
                $.post("/sysUser/editPwd",
                        {id:id,pwd:pass},
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

    function delUser(id) {
        $.post("/sysUser/delUser",
                {id:id},
                function (res) {
                    if (res.code == 200) {
                        layer.msg(res.data);
                        tables.ajax.reload();
                    } else if (res.code == 300) {
                       window.location.href = res.url;
                    } else {
                        layer.msg(res.msg);
                    }
                }
        )
    }

    function addUser() {
        layer.prompt({title: '请输入用户姓名', formType: 3}, function(name, index){
            layer.close(index);
            layer.prompt({title: '请输入密码', formType: 1}, function(pwd, index) {
                $.post("/sysUser/addUser",
                        {name:name,pwd:pwd},
                        function (res) {
                            if (res.code == 200) {
                                layer.alert("用户账号为："+ res.data);
                                tables.ajax.reload();
                            } else {
                                layer.msg(res.msg);
                            }
                        }
                )
                layer.close(index);
            });
        });
    }
</script>
</html>