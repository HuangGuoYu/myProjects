<html>
<head>
    <meta charset="UTF-8">
    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <link rel="stylesheet" href="/resource/css/index.css">
    <link rel="stylesheet" href="/resource/layui-1/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/articleDetail.css"/>
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui-1/layui.js" type="text/javascript"></script>

    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <!-- 轮播插件 -->
    <script type=text/javascript src="/resource/img_turn/js/js.js"></script>
    <style type="text/css" >
        .left-fixed {
            position: fixed;
            top: 45%;
            left: 80px;
            overflow: visible;
        }
    </style>
    <title>文章详情</title>
</head>
<body>
<div class="header-bar clearfloat ">
    <div class="right clearfloat">
        <ul class="clearfloat header-btns">
        <#--<li>-->
        <#--<input class="search-btn clearfloat"/><i class="layui-icon right search-icon">&#xe615;</i>-->
        <#--</li>-->
            <li>
                <a href="/article/writeArticle"><i class="layui-icon write">&#xe642;</i>&nbsp;写博客</a>
            </li>
        <#if isLogin == 0>
            <li>
                <a href="/user/loginPage">登录</a>
            </li>
            <li>
                <a href="/user/registerPage">注册</a>
            </li>
        <#else>
            <li>
                <a href="/user/logout">退出</a>
            </li>
        </#if>
        </ul>
    </div>
</div>
<div class="public-container clearfloat">
    <div class="banner">
        <div class="layui-carousel" id="test1" style="height: 28%;">
            <div carousel-item id="adList">
            <#list ad as item>
                <div><a href="${item.url}"><img src="${item.imageUrl}" style="width: 100%;height: 100%;"></a></div>
            </#list>
            </div>
        </div>
    </div>
    <div class="layui-form-item clearfloat" style="margin-top: 20px;">
        <div class="layui-input-block left" style="width: 60%;">
            <input type="text" name="articleTitle" required  lay-verify="required" placeholder="请输入文章标题" autocomplete="off" class="layui-input">
        </div>
        <span class="layui-btn" style="margin-left: 15px;" onclick="search()">站内搜索</span>
    </div>
    <div class="clearfloat" style="margin-top: 30px;">
        <div class="left articleList" style="width: 74%;background-color: white;min-height: 600px;">
            123
        </div>
        <div class="right" style="width: 25%;background-color: white;min-height: 200px;">
            <div class="income-info" style="margin-top: 20px;">
                <h3 style="border-left: 3px solid red; padding: 5px;margin-bottom: 15px;"><strong>收入排行</strong></h3>
                <ul id="income_rank">
                </ul>
            </div>
        </div>
        <div class="right" style="width: 25%;background-color: white;min-height: 100px;">
            <div class="income-info" style="margin-top: 20px;">
                <h3 style="border-left: 3px solid red; padding: 5px;margin-bottom: 15px;"><strong>收入规则</strong></h3>
                <ul>
                    <li style="margin-top: 10px;"><strong>&nbsp;&nbsp;&nbsp;&nbsp;1.编写高质量文章</strong></li>
                    <li style="margin-top: 10px;"><strong>&nbsp;&nbsp;&nbsp;&nbsp;2.关注者多</strong></li>
                    <li style="margin-top: 10px;"><strong>&nbsp;&nbsp;&nbsp;&nbsp;3.先注册先收益</strong></li>
                </ul>
            </div>
        </div>

        <div class="right" style="width: 25%;background-color: white;min-height: 200px;">
            <div class="income-info" style="margin-top: 20px;">
                <h3 style="border-left: 3px solid red; padding: 5px;margin-bottom: 15px;"><strong>广告</strong></h3>
                <div class="layui-carousel" id="test2" style="height: 28%;">
                    <div carousel-item id="adList">
                    <#list ad as item>
                        <div><a href="${item.url}"><img src="${item.imageUrl}" style="width: 100%;height: 100%;"></a></div>
                    </#list>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
<script type="text/html" id="income_rank_temp">
    {{each data as item i}}
    <li style="padding: 15px;" class="clearfloat">
        <span style="font-size: 20px;">第{{i + 1}}名</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <strong style="font-size: 20px;">{{item.blog_name}}</strong>
        <span class="right" style="color: red;font-size: 20px;">{{item.income}}元</span>
    </li>
    {{/each}}
</script>
<script type="text/html" id="article-item">
    {{each data as item}}
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
            <span>{{item.blog_name}}</span>
            <span class="link_postdate">{{item.postTime}}</span>
            <span class="link_view" title="阅读次数">
                    <span href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706" title="阅读次数">阅读</span>({{item.browse_num}})
                </span>
            <span class="link_comments" title="评论次数">
                    <span href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706#comments" title="评论次数" onclick="_gaq.push(['_trackEvent','function', 'onclick', 'blog_articles_pinglun'])">评论</span>({{item.comment_num}})
                </span>
        </div>
        <div class="clear"></div>
    </div>
    {{/each}}
</script>
<script>
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

        carousel.render({
            elem: '#test2'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });
    });

    function queryData(data) {
        $.post("/article/queryArticleForIndex",data, function (res) {
            if (res.code == 200) {
                var html = template("article-item", res);
                $(".articleList").empty();
                $(".articleList").html(html);
            }
        });
    }

    function search() {
        var data = {}
        data['title'] = $("input[name=articleTitle]").val();
        queryData(data);
    }
    queryData({});
    $.get("/user/incomeRank",function (res) {
        var html = "";
        if (res.code == 200) {
            html = template("income_rank_temp", res)
            $("#income_rank").empty();
            $("#income_rank").html(html);
        }
    });
</script>
<script src="/resource/js/template.js"></script>

</html>