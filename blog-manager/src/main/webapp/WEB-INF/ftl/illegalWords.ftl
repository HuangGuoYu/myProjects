<!
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui-nim/layui.all.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resource/js/common-js.js"></script>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <title>非法词汇</title>
</head>
<body>
<br />
<br />
<br />
<form method="post" action="/file/img" style="width: 400px;">
    <div class="layui-form-item">
        <label class="layui-form-label">目标地址</label>
        <div class="layui-input-block">
            <input id="content" type="text" name="content" required  lay-verify="required" placeholder="请输入禁用词汇" autocomplete="off" class="layui-input">
        </div>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" onclick="submit()">添加词汇</button>
    </div>
</div>
<hr />
<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display" style="text-align: center;">
    <thead>
    <tr>
        <th width="5%">标识</th>
        <th width="40%">名称</th>
        <th width="40%">操作</th>
    </tr>
    </thead>
</table>


</body>
<script>
    var layer = layui.layer;
    var tables;
</script>
<script>
    function submit() {
        var content = $("#content").val();
        console.log(content);
        $.post("/file/addWords", {content:content}, function (res) {
            layer.alert(res.data);
            tables.ajax.reload();
        });
    }

    var colunms = [
        {"data":"id"},
        {"data":"name"},
        {"data":"id",
            "render":function (data, type, row, meta){
               return '<a href="javascript:void(0);" class="layui-btn layui-btn-warm" onclick="delFun('+row.id+')">删除</a>';
            }
        }];

    tables = initTable("table_id_example", "/file/wordsList", "data", colunms);

    function delFun(id) {
        $.post("/file/delWords", {id:id}, function (res) {
            layer.msg(res.data);
            tables.ajax.reload();
        })
    }
</script>
</html>