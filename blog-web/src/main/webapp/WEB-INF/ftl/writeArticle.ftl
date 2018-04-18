<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/writeAricle.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui/layui.js" type="text/javascript"></script>
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
            <a href="/article/writeArticle" class="list-group-item active" data-title="article" target="_self">发布文章</a>
            <a href="/article/cateManager"  class="list-group-item none" target="_self">分类管理</a>
            <a href="/article/articleManager" class="list-group-item none" data-title="comment" target="_self">文章管理</a>
            <a href="https://mp.csdn.net/category/list" class="list-group-item none">个人分类管理</a>
        </div>
    </div>

        <div class="menu_content right">
            <div class="title-box clearfix d-flex">
                <div class="dro-box">
                    <select id="selType">
                        <option value="2">请选择</option>
                        <option value="1">原创</option>
                        <option value="0">转载</option>
                    </select>
                </div>
                <input type="text" id="txtTitle" maxlength="100" placeholder="输入文章标题">
            </div>
            <div class="article_content" id="article_content" >
                <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
            </div>

            <div class="personal_cate">
                <span>个人分类：</span>
                <select id="articleCate" class="articleCate">
                    <option value="0">请选择</option>
                    <#list cates as cate>
                        <option value="${cate.id}">${cate.name}</option>
                    </#list>
                </select>


            </div>
           <div class="submit">
               <button class="layui-btn layui-btn-normal layui-btn-lg" id="submitBlog">发布博客</button>
           </div>
        </div>
    </div>
</div>


</body>
<script src="/resource/editor/wangEditor.min.js"></script>
<script type="text/javascript">
    var layer;
    layui.use('layer', function(){
        layer = layui.layer;
    });

    var E = window.wangEditor;
    var editor = new E('#article_content');
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.create();
    $("div.w-e-text-container").height(600);



    /*发布博客-提交审核*/
    $("#submitBlog").on("click", function () {
        var data = {}
        data.title = $("#txtTitle").val();
        data.isOriginal = $("#selType").val();
        data.content = editor.txt.html();
        data.cateId = $(".articleCate").val();

        if (data.title.length == 0) {
            layer.alert("请输入文章标题");
        }
        if (data.isOriginal == 2) {
            layer.alert("请选择文章类型");
        }
        if (data.content.length == 0) {
            layer.alert("文章内容不可为空");
        }
        if (data.cateId == 0) {
            layer.alert("请选择文章的类别");
        }

        $.ajax({
            url:"/article/addArticle",
            data:data,
            success:function (res) {
                if (res.code == 200) {
                    console.log(res);
                    layer.alert(res.data);
                    $("#txtTitle").val("");
                    $("#selType").val(2);
                    editor.txt.html("");
                    $(".articleCate").val(0);
                } else {
                    layer.alert(res.msg);
                }
            },
            dataType:"json"
        })
    })
</script>
</html>