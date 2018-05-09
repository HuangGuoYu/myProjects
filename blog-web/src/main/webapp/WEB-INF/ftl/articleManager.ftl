<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/writeAricle.css">
    <link rel="stylesheet" href="/resource/css/articleManager.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui/layui.js" type="text/javascript"></script>

    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <style>
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
            <a href="#"><img src="${uinfo.headIcon}" rel="wu" style="border-radius: 50%;display: inline-block;height: 71px; width: 71px;"></a>
            <span class="blog-name" style="">${data.blogName}</span>
        </div>
        <a class="exit right" style="cursor:pointer;" href="/user/logout">
            退出
        </a>
    </div>
</div>

<div class="content clearfloat">

    <div class="public-container clearfloat" style="background-color: #fff">
        <div class="nav_menu left">
            <div class="list-group-item list-group-title">内容</div>
            <div class="list-group">
                <a href="/article/writeArticle" class="list-group-item" data-title="article" target="_self">发布文章</a>
                <a href="/article/cateManager"  class="list-group-item none" target="_self">分类管理</a>
                <a href="/article/articleManager" class="list-group-item none active" data-title="comment" target="_self">文章管理</a>
                <a href="/user/userCenter" class="list-group-item none">个人中心</a>
            </div>
        </div>

        <div class="menu_content right">
            <div class="main-crumbs">文章管理</div>
            <div class="main_tab_nav clearfloat">
                <ul class="clearfloat">
                    <li><a id="deploy" class="nav-link active" onclick="switchTab(this,1)">已发表文章</a></li>
                    <li><a class="nav-link" onclick="switchTab(this,0)">审核中</a></li>
                    <li><a class="nav-link" onclick="switchTab(this,2)">回收站</a></li>
                </ul>

                <div class="article_list">
                    <div class="group-list" id="group-list">
                        <div class="list-item">
                            <div class="item-header">
                                <h1>this is my blog name</h1>
                            </div>
                            <div class="item-foot clearfloat">
                                <div class="item-foot-left left">
                                    <span>原创</span>
                                    <span><i class="layui-icon" style="color: red">&#xe637;</i>&nbsp;2017-02-08</span>
                                    <span><i class="layui-icon" style="color: red">&#xe63c;</i>&nbsp;阅读量</span>
                                    <span><i class="layui-icon" style="color: red">&#xe63a;</i>&nbsp;评论数</span>
                                </div>
                                <div class="item-foot-right right">
                                    <span><a>查看</a></span>
                                    <span><a>删除</a></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <nav aria-label="Page navigation" class="page-nav-outer" id="PageNavId" style="margin-top: 20px"></nav>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<script type="text/html" id="article-item">
    {{each datas as item}}
    <div class="list-item">
        <div class="item-header">
            <h1>{{item.title}}</h1>
        </div>
        <div class="item-foot clearfloat">
            <div class="item-foot-left left">
                <span>{{item.isOriginal == 0 ? '转载': '原创'}}</span>
                <span><i class="layui-icon" style="color: red">&#xe637;</i>&nbsp;{{item.strTime}}</span>
                <span><i class="layui-icon" style="color: red">&#xe63c;</i>&nbsp;阅读量({{item.browseNum}})</span>
                <span><i class="layui-icon" style="color: red">&#xe63a;</i>&nbsp;评论数({{item.commentNum}})</span>
            </div>
            <div class="item-foot-right right">
                <span><a href="/article/articleDetail?id={{item.id}}">查看</a></span>
                <span><a href="/article/editArticlePage?id={{item.id}}">编辑</a></span>
                <span><span style="cursor: pointer;" onclick="delArticle({{item.id}})">删除</span></span>
            </div>
        </div>
    </div>
    {{/each}}
</script>

<script type="text/html" id="article-item-cert">
    {{each datas as item}}
    <div class="list-item">
        <div class="item-header">
            <h1>{{item.title}}</h1>
        </div>
        <div class="item-foot clearfloat">
            <div class="item-foot-left left">
                <span>{{item.isOriginal == 0 ? '转载': '原创'}}</span>
                <span><i class="layui-icon" style="color: red">&#xe637;</i>&nbsp;{{item.strTime}}</span>
            </div>
            <div class="item-foot-right right">
                <#--<span><a href="/article/articleManager">查看</a></span>-->
            </div>
        </div>
    </div>
    {{/each}}
</script>

<script type="text/html" id="article-item-back">
    {{each datas as item}}
    <div class="list-item">
        <div class="item-header">
            <h1>{{item.title}}</h1>
        </div>
        <div class="item-foot clearfloat">
            <div class="item-foot-left left">
                <span>{{item.isOriginal == 0 ? '转载': '原创'}}</span>
                <span><i class="layui-icon" style="color: red">&#xe637;</i>&nbsp;{{item.strTime}}</span>
                <span><i class="layui-icon" style="color: red">&#xe63c;</i>&nbsp;阅读量({{item.browseNum}})</span>
                <span><i class="layui-icon" style="color: red">&#xe63a;</i>&nbsp;评论数({{item.commentNum}})</span>
            </div>
            <div class="item-foot-right right">
                <span><span onclick="delFromDisk({{item.id}})">删除</span></span>
                <span><span style="cursor: pointer;" onclick="renewArticle({{item.id}})">恢复</span></span>
            </div>
        </div>
    </div>
    {{/each}}
</script>

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
            case 0: //审核中，状态未删除
                ajax4List(state, curPage)
                break;
            case 1: //已发表，未删除
                ajax4List(state, curPage)
                break;
            case 2: //回收站，已删除
                ajax4List(state, curPage)
                break;
        }
    }

    function ajax4List(state, curPage) {
        $.ajax({
            url:"/article/queryArticleByState",
            type:"post",
            data:{state:state, curPage:curPage},
            success:function (res) {
                initPageNav(res.data)
            },
            dataType:"json"
        });
    }



    function initPageNav(res) {
        var templateName = "article-item-cert";
        if (curState == 0) {
            templateName = "article-item-cert"
        } else if (curState == 1) {
            templateName = "article-item";
        } else {
            templateName = "article-item-back";
        }
        for(item in res.datas) {
//            console.log(new Date(res.datas[item].postTime).toLocaleString())
            res.datas[item].strTime = new Date(res.datas[item].postTime).toLocaleString();
        }
        var pageNavObj = null;//用于储存分页对象
        console.log(res)
        pageNavObj = new PageNavCreate("PageNavId",{
            pageCount:res.pageCount,
            currentPage:res.curPage,
            perPageNum:res.perCount,
        });
        pageNavObj.afterClick(pageNavCallBack);
        var html = template(templateName, res);
        $("#group-list").empty();
        $("#group-list").html(html);
    }

    function pageNavCallBack(clickPage){
//        pageNavObj = new PageNavCreate("PageNavId",{
//            pageCount:30,
//            currentPage:clickPage,
//            perPageNum:5,
//        });
//        pageNavObj.afterClick(pageNavCallBack);
            switchTab(curTab, curState, clickPage);
    }

    function delArticle(id) {
        $.post("/article/delArticle", {id:id}, function (res) {
            if (res.code == 200) {
                layer.msg(res.data);
                switchTab($(".nav-link.active"), 1, 1);
            } else {
                layer.alert(res.msg);
            }
        })
    }

    function renewArticle(id) {
        $.post("/article/renewArticle", {id:id}, function (res) {
            if (res.code == 200) {
                layer.msg(res.data);
                switchTab($(".nav-link.active"), 1, 1);
                $(".nav-link.active").removeClass("active");
                $("#deploy").addClass("active");
            } else {
                layer.alert(res.msg);
            }
        });
    }

    function delFromDisk(id) {
        $.post("/article/delFromDisk", {id:id}, function (res) {
            if (res.code == 200) {
                layer.msg(res.data);
                switchTab($(".nav-link.active"), 1, 1);
                $(".nav-link.active").removeClass("active");
                $("#deploy").addClass("active");
            } else {
                layer.alert(res.msg);
            }
        });
    }
</script>
</html>