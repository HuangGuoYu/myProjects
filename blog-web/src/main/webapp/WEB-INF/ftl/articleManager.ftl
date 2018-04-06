<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/writeAricle.css">
    <link rel="stylesheet" href="/resource/css/articleManager.css">
    <script type=text/javascript src="/resource/js/jquery-3.1.1.min.js"></script>
    <script src="/resource/layui/layui.all.js" type="text/javascript"></script>

    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
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
                <a href="/article/cateManager"  class="list-group-item none" target="_self">分类管理</a>
                <a href="/article/articleManager" class="list-group-item none active" data-title="comment" target="_self">文章管理</a>
                <a href="https://mp.csdn.net/category/list" class="list-group-item none">个人分类管理</a>
            </div>
        </div>

        <div class="menu_content right">
            <div class="main-crumbs">文章管理</div>
            <div class="main_tab_nav clearfloat">
                <ul class="clearfloat">
                    <li><a class="nav-link active" onclick="switchTab(1)">已发表文章</a></li>
                    <li><a class="nav-link" onclick="switchTab(0)">审核中</a></li>
                    <li><a class="nav-link" onclick="switchTab(3)">回收站</a></li>
                </ul>

                <div class="article_list">
                    <div class="group-list">
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
<script type="text/html" id="test">
    {{each list as item}}
        <div>{{item}}</div>
    {{/each}}
</script>
<script src="/resource/editor/wangEditor.min.js"></script>
<script src="/resource/js/template.js"></script>
<script type="text/javascript">
    var layer;
    layui.use('layer', function(){
        layer = layui.layer;
    });

    function switchTab(state) {
        switch (state) {
            case 0: //审核中，状态未删除

                break;
            case 1: //已发表，未删除

                break;
            case 2: //回收站，已删除

                break;
        }
    }



    function initPageNav(pageCount, currentPage, perPageNum) {
        var pageNavObj = null;//用于储存分页对象
        pageNavObj = new PageNavCreate("PageNavId",{
            pageCount:30,
            currentPage:1,
            perPageNum:5,
        });
        pageNavObj.afterClick(pageNavCallBack);
    }
    function pageNavCallBack(clickPage){
        pageNavObj = new PageNavCreate("PageNavId",{
            pageCount:30,
            currentPage:clickPage,
            perPageNum:5,
        });
        pageNavObj.afterClick(pageNavCallBack);
    }
</script>
</html>