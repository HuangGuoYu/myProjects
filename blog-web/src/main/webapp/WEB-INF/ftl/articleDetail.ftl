<html>
<head>
    <meta charset="UTF-8">
    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <link rel="stylesheet" href="/resource/css/index.css">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/articleDetail.css"/>
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui/layui.js" type="text/javascript"></script>

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
    <title>Document</title>
</head>
<body>
    <div class="left-fixed">
        <ul class="fixed-icon">
            <li title="点赞" onclick="likeFun(this)" class="<#if likeState == 0>none<#else>fixed-icon-active</#if>"><i class="layui-icon">&#xe6af;</i></li>
            <li title="聊天" onclick="chat('${data.user.id}')"><i class="layui-icon">&#xe611;</i></li>
            <li title="收藏" onclick="collectionArticle()"><i class="layui-icon">&#xe600;</i> </li>
        </ul>
    </div>

    <div class="header-bar clearfloat">
        <div class="right clearfloat">
            <ul class="clearfloat header-btns">
                <li>
                    <input class="search-btn clearfloat"/><i class="layui-icon right search-icon">&#xe615;</i>
                </li>
                <li>
                    <a><i class="layui-icon write">&#xe642;</i>&nbsp;写博客</a>
                </li>
                <#if isLogin == 0>
                    <li>
                        <a>登录</a>
                    </li>
                    <li>
                        <a>注册</a>
                    </li>
                <#else>
                    <li>
                        <a>退出</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>

    <div class="public-container clearfloat content">
        <div class="article-content left clearfloat">

            <h1>${data.article.title}</h1>
            <div class="content-header">
                <div class="tag left">
                    <span class="is-original"><#if data.article.isOriginal == 0>转载<#else>原创</#if></span>
                    <span class="time">${data.article.postTime?string("yyyy-MM-dd hh:mm:ss")}</span>
                </div>
                <div class="right">
                    <span class="icon"><i class="layui-icon">&#xe705;</i></span>
                    <span class="b-num">${data.article.browseNum}</span>
                </div>
            </div>
            <hr>

            <div class="real-content">
                ${data.article.content}
            </div>
            <hr>
            <#if isLogin == 0>
            <div class="guest_link">
                <span class="txt">目前您尚未登录，请 <a href="">登录</a> 或 <a href="">注册</a> 后进行评论
                </span>
            </div>
            <#else>
            <div class="layui-form-item">
                <label class="layui-form-label">评论</label>
                <div class="layui-input-block">
                    <input id = "comment_content" type="text" name="content" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input" style="width: 90%">
                    <a class="layui-btn layui-btn-danger send-btn" onclick="sendComment('${data.article.id}')">发表评论</a>
                </div>

                <div class="comment-content">
                    <h2 class="see-comment">查看评论</h2>
                    <hr>
                    <div id="item_container">
                    </div>
                </div>
            </div>
            </#if>
        </div>

        <div class="author-info right">
            <div class="info">
                <div class="user_info">
                    <div class="info_bar">
                        <a href="#"><img src="/resource/imgs/headImg.jpg" rel="wu"></a>
                        <span class="blog-name" style="">的博客</span>
                        <span class="btn-like layui-btn layui-btn-normal" onclick="attention('${data.user.id}')">关注</span>
                    </div>
                    <div class="info_num_box clearfloat">
                        <dl title="26">
                            <dt>原创</dt>
                            <dd>${data.user.originalNum}</dd>
                        </dl>
                        <dl title="1">
                            <dt>粉丝</dt>
                            <dd id="fan">${data.user.attention_num}</dd>
                        </dl>
                        <dl title="23">
                            <dt>评论</dt>
                            <dd>${data.user.comment_num}</dd>
                        </dl>
                    </div>
                </div>
            </div>
            <!-- 广告 -->
            <div class="area">
                <a id=prev class="prevBtn qq" href="javascript:void(0)"></a>
                <a id=next class="nextBtn qq" href="javascript:void(0)"></a>
                <div id=js class="js">
                    <div class="box01">
                        <img onClick="location.href='http://www.jq22.com/'"  src="/resource/img_turn/images/01.jpg">
                        <img onClick="location.href='http://www.jq22.com/'"  style="DISPLAY: none" src="/resource/img_turn/images/02.jpg">
                        <img onClick="location.href='http://www.jq22.com/'" style="DISPLAY: none"  src="/resource/img_turn/images/03.jpg">
                        <img onClick="location.href='http://www.jq22.com/'" style="DISPLAY: none"  src="/resource/img_turn/images/04.jpg">
                        <img onClick="location.href='http://www.jq22.com/'" style="DISPLAY: none" src="/resource/img_turn/images/05.jpg">
                    </div>
                    <div class="bg"></div>
                    <div id=jsNav class=jsNav>
                        <a class="trigger imgSelected" href="javascript:void(0)">1</a>
                        <a class="trigger" href="javascript:void(0)">2</a>
                        <a class="trigger" href="javascript:void(0)">3</a>
                        <a class="trigger" href="javascript:void(0)">4</a>
                        <a class="trigger" href="javascript:void(0)">5</a>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>

</body>
<script src="/resource/js/template.js"></script>

<script type="text/html" id="comment_item_tmp">
    {{each data as item}}
        <div class="comment-item">
            <div><span data-id="" class="comment-blog-name">{{item.blog_name}}</span><span>{{item.postTime}}发布</span></div>
            <h3 class="comment-content">{{item.content}}</h3>
        </div>
    {{/each}}
</script>
<script>
    var aid = ${data.article.id};
    var layer;
    layui.use('layer', function(){
        layer = layui.layer;
    });

    //    <!-- 启动轮播 -->
    $(document).ready(function(){
        $(".area").hover(function(){
                    $(this).find(".qq").show(100);}
                ,function(){
                    $(this).find(".qq").hide(100);
                });
    });

    function sendComment(id) {
        var content = $("#comment_content").val();
        $.post("/comment/add", {aid:id, content:content}, function (res) {
            layer.alert(res.msg);
            $("#comment_content").val("");
            if (res.code == 200) {
                getCommentData();
            }
        })
    }

    function getCommentData() {
        $.post("/comment/queryDataByAid", {aid:aid}, function (res) {
            if (res.code == 200) {
                $obj = $("#item_container");
                $obj.empty();
                for (item in res.data) {
                    res.data[item].postTime = new Date(res.data[item].comment_time).toLocaleString();
                }
                $obj.html(template("comment_item_tmp", res));
            }
        })
    }
    //刷新页面调用
    getCommentData();

    /**
     * 点赞进行
     */
    function likeFun(obj) {
        console.log(obj)
        $.post("/article/likeArticle", {aid:aid}, function (res) {
           if (res.code == 200) {
               $(obj).addClass("fixed-icon-active");
           } else {
               layer.msg(res.msg);
           }
        });
    }

    /**
     * 文章收藏
     */
    function collectionArticle() {
        if (isLogin == 0) {
            window.location.href = "/user/loginPage";
        }
        $.post("/collect/add",{aid:aid},function (res) {
            console.log(res);
            layer.alert(res.msg);
        })
    }


    var isLogin = ${isLogin};
    /**
     * 关注
     * @param uid
     */
    function attention(uid) {
        if (isLogin == 0) {
            window.location.href = "/user/loginPage";
        }
        $.post("/userRel/add", {toUser:uid}, function (res) {
            layer.alert(res.msg)
        })
    }

    function chat(id) {
        if (isLogin == 0) {
            window.location.href = "/user/loginPage";
        }
        window.location.href = "/chat/page?id=" + id;
    }

</script>
</html>