<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">


    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/resource/layui-1/css/layui.css">
    <script src="/resource/layui-1/layui.js" type="text/javascript"></script>
    <script type=text/javascript src="/resource/img_turn/js/js.js"></script>
    <!-- 轮播插件 -->
    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
<#--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->


    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/index.css"/>
    <style type="text/css">
        .red{
            color: red;
        }
    </style>
    <title>${data.blogName}的博客</title>
</head>
<body>
    <div class="index-header public-container">
        <div class="blog-name">
            <a href="#">${data.blogName}的博客</a>
        </div>
    </div>
    <!-- 菜单 -->
    <div class="index-menu-nav public-container clearfloat">
        <ul>
            <li><a href="/user/userCenter">个人中心</a></li>
            <li><i class="layui-icon <#if isExistsMsg == 1>red</#if>">&#xe756;</i><a href="/chat/page">&nbsp;我的消息</a></li>
            <li><a href="/income/index">收益管理</a></li>
            <li><a href="#">我的博客</a></li>
            <li><a href="/article/writeArticle">写博客</a></li>
        </ul>
    </div>

    <div class="index-content public-container clearfloat">
        <div class="left-content left" style="width: 250px;margin-bottom: 9px;background: #fff;">
            <div class="left-content-personal-info">
                <div class="title">个人信息</div>
            </div>
            <div class="info">
                <div class="user_info">
                    <div class="info_bar">
                        <a href="#"><img src="${userDetail.headIcon}" rel="wu"></a>
                        <span class="blog-name" style="">${data.blogName}的博客</span>
                    </div>
                    <div class="info_num_box clearfloat">
                        <dl title="26">
                            <dt>原创</dt>
                            <dd>${data.fansNum}</dd>
                        </dl>
                        <dl title="1">
                            <dt>粉丝</dt>
                            <dd id="fan">${data.attentionNum}</dd>
                        </dl>
                    </div>
                </div>
            </div>

            <div class="layui-carousel" id="test1">
                <div carousel-item>
                    <div><img src="${userDetail.headIcon}"></div>
                    <div><img src="${userDetail.headIcon}"></div>
                    <div><img src="${userDetail.headIcon}"></div>
                    <div><img src="${userDetail.headIcon}"></div>
                    <div><img src="${userDetail.headIcon}"></div>
                </div>
            </div>

            <div class="income-info" style="margin-top: 20px;">
                <h3 style="border-left: 3px solid red; padding: 5px;margin-bottom: 15px;"><strong>收入排行</strong></h3>
                <ul id="income_rank">
                </ul>
            </div>

            <div class="personal-cate" style="margin-top: 20px;">
                <h3 style="border-left: 3px solid red; padding: 5px;margin-bottom: 15px;"><strong>个人分类</strong></h3>
                <ul id="personal-cate">
                </ul>
            </div>

        </div>




        <div class="right-content right" style="width: 780px;background: #fff;">
            <div id="group-list"></div>
            <nav aria-label="Page navigation" class="page-nav-outer" id="PageNavId"></nav>
        </div>
    </div>
</body>
<script src="/resource/js/template.js"></script>

<script type="text/html" id="article-item">
    {{each datas as item}}
        <div class="list_item clearfloat article_item">
            <div class="article_title">
                <span class="ico ico_type_Original"></span>
                <h1>
                    <span class="link_title">
                       <a href="/article/articleDetail?id={{item.id}}">{{item.title}}</a>
                    </span>
                </h1>
                <div class="article_description">{{item.title}}...</div>
            </div>
            <div class="article_manage">
                <span class="link_postdate">{{item.strTime}}</span>
                <span class="link_view" title="阅读次数">
                    <span href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706" title="阅读次数">阅读</span>({{item.browseNum}})
                </span>
                <span class="link_comments" title="评论次数">
                    <span href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706#comments" title="评论次数" onclick="_gaq.push(['_trackEvent','function', 'onclick', 'blog_articles_pinglun'])">评论</span>({{item.commentNum}})
                </span>
            </div>
            <div class="clear"></div>
        </div>
    {{/each}}
</script>

<script type="text/javascript">
//    <!-- 启动轮播 -->

    $(document).ready(function(){
        layui.use('carousel', function(){
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#test1'
                ,width: '100%' //设置容器宽度
                ,arrow: 'always' //始终显示箭头
                //,anim: 'updown' //切换动画方式
            });
        });

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
            var templateName = "article-item";
            for(item in res.datas) {
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
            //实现也面跳转
            ajax4List(1, clickPage);
        }
        //初始化第一页
        ajax4List(1, 1);
    });

    $.get("/user/incomeRank",function (res) {
        var html = "";
        if (res.code == 200) {
            html = template("income_rank_temp", res)
            $("#income_rank").empty();
            $("#income_rank").html(html);
        }
    });

    $.get("/user/personalCate", function (res) {
        var html1 = "";
        if (res.code == 200) {
            html1 = template("personal_cate_temp", res);
            $("#personal-cate").empty();
            $("#personal-cate").html(html1);
        }
    })

</script>

<script type="text/html" id="income_rank_temp">
    {{each data as item i}}
        <li style="padding: 15px;" class="clearfloat">
            <span style="font-size: 20px;">第{{i + 1}}名</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <strong style="font-size: 20px;">{{item.blog_name}}</strong>
            <span class="right" style="color: red;font-size: 20px;">{{item.income}}元</span>
        </li>
    {{/each}}
</script>

<script type="text/html" id="personal_cate_temp">
    {{each data as item i}}
    <li style="padding: 10px;" class="clearfloat">
        <#--<span style="font-size: 10px;">第{{i + 1}}</span>&nbsp;&nbsp;&nbsp;&nbsp;-->
        <strong style="font-size: 10px;">{{item.name}}</strong>
        <span class="right" style="font-size: 10px;">{{item.count}}</span>
    </li>
    {{/each}}
</script>
</html>