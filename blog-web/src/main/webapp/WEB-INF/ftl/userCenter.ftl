<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui-1/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/writeAricle.css">
    <link rel="stylesheet" href="/resource/css/articleManager.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui-1/layui.js" type="text/javascript"></script>

    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <style type="text/css">
        input{
            width: 100%;
        }
        img{
            border-radius: 50%;
            display: inline-block;
            height: 71px; width: 71px;
        }
    </style>
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

    <div class="public-container clearfloat" style="background-color: #fff">
        <div class="nav_menu left">
            <div class="list-group-item list-group-title">内容</div>
            <div class="list-group">
                <a href="/article/writeArticle" class="list-group-item" data-title="article" target="_self">发布文章</a>
                <a href="/article/cateManager"  class="list-group-item none" target="_self">分类管理</a>
                <a href="/article/articleManager" class="list-group-item none" data-title="comment" target="_self">文章管理</a>
                <a href="/user/userCenter" class="list-group-item none active">个人中心</a>
            </div>
        </div>

        <div class="menu_content right">
            <div class="main-crumbs">个人中心</div>
            <div class="main_tab_nav clearfloat">
                <ul class="clearfloat">
                    <li><a class="nav-link" onclick="switchTab(this,0)">我的信息</a></li>
                    <li><a class="nav-link active" onclick="switchTab(this,1)">我的收藏</a></li>
                    <li><a class="nav-link" onclick="switchTab(this,2)">我的关注</a></li>
                </ul>


                <div class="article_list">
                    <div class="userInfo">
                        <table class="layui-table">
                            <colgroup>
                                <col width="150">
                                <col width="200">
                                <col>
                            </colgroup>
                            <tr>
                                <th>名称</th>
                                <th>内容</th>
                            </tr>
                            <tbody>
                            <tr>
                                <td>QQ号</td>
                                <td><input class="input" type="text" value="${uinfo.qq!}" name="qq"></td>
                            </tr>
                            <tr>
                                <td>微信号</td>
                                <td><input class="input" type="text" value="${uinfo.wx!}" name="wx"></td>
                            </tr>
                            <tr>
                                <td>电话</td>
                                <td><input class="input" type="text" value="${uinfo.phone!}" name="phone"></td>
                            </tr>
                            <tr>
                                <td>专业领域</td>
                                <td><input class="input" type="text" value="${uinfo.expertField!}" name="expertField"></td>
                            </tr>
                            <tr>
                                <td>专业技术</td>
                                <td><input class="input" type="text" value="${uinfo.expertTech!}" name="expertTech"></td>
                            </tr>
                            <tr>
                                <td>学历</td>
                                <td><input class="input" type="text" value="${uinfo.education!}" name="education"></td>
                            </tr>
                            <tr>
                                <td>头像</td>
                                <td>
                                    <img src="${uinfo.headIcon}" rel="wu" id="headerIcon">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="layui-btn" id="test1">
                                        <i class="layui-icon">&#xe67c;</i>上传图片
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <button class="layui-btn layui-btn-normal" style="" onclick="submitUserInfo()">保存基本信息</button>

                    </div>

                    <!--第二步：添加如下 HTML 代码-->
                    <table id="table_id_example" class="display" style="text-align: center;">
                        <thead>
                        </thead>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<script src="/resource/editor/wangEditor.min.js"></script>
<script src="/resource/js/template.js"></script>
<script type="text/javascript">
    var layer;
    layui.use('layer', function(){
        layer = layui.layer;
    });
    var curTab;
    var curState;
    switchTab($(".nav-link.active"), 1, 1);
    function switchTab(obj, state, curPage = 1) {
        //切换样式状态
        curState = state;
        curTab = obj;
        $(".nav-link.active").removeClass("active");
        $(obj).addClass("active");
        switch (state) {
            case 0: //我的信息
                $("#table_id_example_wrapper").hide();
                $(".userInfo").show();
                break;
            case 1: //我的搜藏
                var $thead = $("thead");
                $(".userInfo").hide();
                $thead.empty();
                $thead.html("<tr><th>文章编号</th><th>文章名</th><th>查看</th><th>操作</th></tr>");
                var columns = [
                    {
                        data:"articleId"
                    },{
                        data:"title"
                    },{
                        data:"articleId",
                        "render":function (data, type, row, meta) {
                            return '<a href = "' + data +'">查看</a>';
                        }
                    },{
                        data:"id",
                        "render":function (data, type, row, meta) {
                            return '<a href = "' + data +'">删除</a>';
                        }
                    }
                ];
                initDataByTable("/user/queryUserLikeArticle", columns);
                break;
            case 2: //我的关注
                var $thead = $("thead");
                $(".userInfo").hide();
                $thead.empty();
                $thead.html("<tr><th>编号</th><th>博主</th><th>查看</th></tr>");
                var columns = [
                    {
                        data:"to_user_id",
                        "render":function (data, type, row, meta) {
                            return data + "";
                        }
                    },{
                        data:"blog_name"
                    },{
                        data:"to_user_id",
                        "render":function (data, type, row, meta) {
                            return '<a href = "' + data +'">查看</a>';
                        }
                    }
                ];
                initDataByTable("/user/queryUserAttention", columns);
                break;
        }
    }


    /*初始化表格数据**/
    var tables;

    function initDataByTable(url, columns) {
        tables = $('#table_id_example').DataTable({
            ajax:{
                url:url,
                dataSrc:"data"
            },
            columns:columns,
            language: i18n,
            destroy:true
        });
    }

    var i18n = {
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
    };

    function submitUserInfo() {
        var data = {};
        var $inputs = $(".input");
        console.log($inputs);
        $inputs.each(function () {
           data[$(this).attr("name")] = $(this).val();
        });
        console.log(data);
        $.post(
            "/user/saveUserDetail",
            data,
            function (res) {
                if (res.code == 200) {
                    layer.alert(res.data)
                } else {
                    layer.alert(res.msg)
                }
            }
        );
    }
</script>

<script>
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/user/uploadImage' //上传接口
            ,done: function(res){
                //上传完毕回调
                if (res.code == 200) {
                    $("#headerIcon").attr("src", res.data);
                } else {
                    layer.alert(res.msg);
                }
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });
</script>
</html>