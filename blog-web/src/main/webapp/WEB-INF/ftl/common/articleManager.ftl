<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/writeAricle.css">
    <link rel="stylesheet" href="/resource/css/cateManager.css">
    <script type=text/javascript src="/resource/js/jquery-3.1.1.min.js"></script>
    <script src="/resource/layui/layui.all.js" type="text/javascript"></script>

    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
</head>

<body style="background-color: #f4f4f4">
<div class="header">
    <!-- 顶部 -->
    <div class="header-bar public-container clearfloat">
        <div class="info_bar left">
            <a href="#"><img src="/resource/imgs/headImg.jpg" rel="wu"></a>
            <span class="blog-name" style="">${data.blogName}</span>
        </div>
        <div class="exit right">
            退出
        </div>
    </div>
</div>

<div class="content clearfloat">

    <div class="public-container">
        <div class="nav_menu left">
            <div class="list-group-item list-group-title">内容</div>
            <div class="list-group">
                <a href="/article/writeArticle" class="list-group-item" data-title="article" target="_self">发布文章</a>
                <a href="/article/cateManager" target="_blank" class="list-group-item none active" arget="_self">分类管理</a>
                <a href="https://mp.csdn.net/comment/list" class="list-group-item none" data-title="comment" arget="_self">文章管理</a>
                <a href="https://mp.csdn.net/category/list" class="list-group-item none">个人分类管理</a>
            </div>
        </div>

        <div class="menu_content right">
            <div class="add-categorie-box clearfloat">
                <div class="right">
                    <div class="form-group row d-flex align-items-center">
                        <div class="txt-box" style="display: inline-block;">
                            <input type="text" class="form-control form-control-sm feedback-icon" id="cateName">
                        </div>
                        <button class="btn layui-btn layui-btn-normal" onclick="addCate()">添加分类</button>
                    </div>
            </div>
        </div>

            <!--第二步：添加如下 HTML 代码-->
            <table id="table_id_example" class="display" style="text-align: center;">
                <thead>
                    <tr>
                        <th>唯一标示</th>
                        <th>名称</th>
                        <#--<th>排序</th>-->
                        <th>文章数量</th>
                        <th>操作</th>
                    </tr>
                </thead>
            </table>
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

    /**
     * 添加分类
     */
    function addCate() {
        var name = $("#cateName").val();
        var data = {};
        data.name = name;
        if (name.length == 0) {
            layer.alert("请输入分类名称");
        }
        $.ajax({
            url:"/article//addCategory",
            data:data,
            success:function (res) {
                if (res.code == 200) {
                    layer.alert(res.msg);
                    tables.ajax.reload();
                    $("#cateName").val("");
                } else {
                    layer.alert(res.msg);
                }
            },
            dataType:"json"
        })
    }


    function delCate(id) {
        layer.confirm(
            "你确定删除改分类,这将使得该分类下的所有数据被删除！！！",
                {btn:["确定","取消"]},
                function () {
                    layer.msg("ok")
                    $.ajax({
                        url:"/article/delCategory",
                        data:{id:id},
                        success:function (res) {
                            if (res.code == 200) {
                                layer.alert(res.msg);
                                tables.ajax.reload();
                                $("#cateName").val("");
                            } else {
                                layer.alert(res.msg);
                            }
                        },
                        dataType:"json"
                    })
                },function () {
                    layer.msg("cancle")
                }
        );
    }


    function editCate(id) {
        layer.prompt({title: '请输入新分类名称', formType: 1}, function(pass, index){
            if (pass.length == 0) {
                layer.msg("分类名称不能为空");
            } else {
                $.ajax({
                    url:"/article/editCategory",
                    data:{id:id, name:pass},
                    success:function (res) {
                        if (res.code == 200) {
                            layer.msg(res.msg);
                            tables.ajax.reload();
                            layer.close(index);
                        } else {
                            layer.alert(res.msg);
                        }
                    },
                    dataType:"json"
                })
            }
        });
    }




    <!--第三步：初始化Datatables-->
    $(document).ready( function () {
       tables = $('#table_id_example').DataTable({
            ajax:{
                url:"/article/allCate",
                dataSrc:"data"
            },
            columns:[
                {data:"id"},
                {data:"name"},
//                {data:"ord"},
                {data:"articleNum"},
                {
                    data:"id",
                    "render":function (data, type, row, meta) {
                        console.log(data);
                        return "<a class='edit' onclick='editCate(" + data + ")'>编辑</a><a class='del' onclick='delCate(" + data + ")'>删除</a>";
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