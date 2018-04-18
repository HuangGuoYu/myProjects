<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">


    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui/layui.js" type="text/javascript"></script>
    <script type=text/javascript src="/resource/img_turn/js/js.js"></script>
    <!-- 轮播插件 -->
    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
<#--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->


    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/index.css"/>

    <title>张三的博客</title>
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
            <li>个人中心</li>
            <li>我的消息</li>
            <li>收益管理</li>
            <li>我的博客</li>
            <li>账号管理</li>
            <li><a href="/article/writeArticle">写博客</a></li>
        </ul>
    </div>

    <div class="index-content public-container clearfloat">
        <div class="left-content left" style="width: 450px;margin-bottom: 9px;background: #fff;">
            <div class="left-content-personal-info">
                <div class="title">个人信息</div>
            </div>
            <div class="info">
                <div class="user_info">
                    <div class="info_bar">
                        <a href="#"><img src="/resource/imgs/headImg.jpg" rel="wu"></a>
                        <span class="blog-name" style="">${data.blogName}的博客</span>
                    </div>
                    <div class="info_num_box clearfloat">
                        <dl title="26">
                            <dt>原创</dt>
                            <dd>26</dd>
                        </dl>
                        <dl title="1">
                            <dt>粉丝</dt>
                            <dd id="fan">1</dd>
                        </dl>
                        <dl title="3">
                            <dt>喜欢</dt>
                            <dd>3</dd>
                        </dl>
                        <dl title="23">
                            <dt>评论</dt>
                            <dd>23</dd>
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


        <div class="right-content right" style="width: 880px;background: #fff;">

            <div class="list_item clearfloat article_item">
                <div class="article_title">
                    <span class="ico ico_type_Original"></span>
                    <h1>
                        <span class="link_title">
                           <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706">spring+springmvc+mybatis(下)</a>
                        </span>
                    </h1>
                    <div class="article_description">spring+Mybatis+springMVC的第二部分</div>
                </div>
                <div class="article_manage">
                    <span class="link_postdate">2017-05-09 09:28:13</span>
                    <span class="link_view" title="阅读次数">
                        <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706" title="阅读次数">阅读</a>(744)
                     </span>
                    <span class="link_comments" title="评论次数">
                        <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706#comments" title="评论次数" onclick="_gaq.push(['_trackEvent','function', 'onclick', 'blog_articles_pinglun'])">评论</a>(0)
                    </span>
                    <span class="link_edit"><a href="https://mp.csdn.net/postedit/71438706" title="编辑">编辑</a></span>
                    <span class="link_edit"><a href="javascript:void(0);" onclick="javascript:topArticle(71438706);return false;" title="置顶">置顶</a></span>
                    <span class="link_delete"><a href="javascript:void(0);" onclick="javascript:deleteArticle(71438706);return false;" title="删除">删除</a></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_item clearfloat article_item">
                <div class="article_title">
                    <span class="ico ico_type_Original"></span>
                    <h1>
                        <span class="link_title">
                           <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706">spring+springmvc+mybatis(下)</a>
                        </span>
                    </h1>
                    <div class="article_description">spring+Mybatis+springMVC的第二部分</div>
                </div>
                <div class="article_manage">
                    <span class="link_postdate">2017-05-09 09:28:13</span>
                    <span class="link_view" title="阅读次数">
                        <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706" title="阅读次数">阅读</a>(744)
                     </span>
                    <span class="link_comments" title="评论次数">
                        <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706#comments" title="评论次数" onclick="_gaq.push(['_trackEvent','function', 'onclick', 'blog_articles_pinglun'])">评论</a>(0)
                    </span>
                    <span class="link_edit"><a href="https://mp.csdn.net/postedit/71438706" title="编辑">编辑</a></span>
                    <span class="link_edit"><a href="javascript:void(0);" onclick="javascript:topArticle(71438706);return false;" title="置顶">置顶</a></span>
                    <span class="link_delete"><a href="javascript:void(0);" onclick="javascript:deleteArticle(71438706);return false;" title="删除">删除</a></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_item clearfloat article_item">
                <div class="article_title">
                    <span class="ico ico_type_Original"></span>
                    <h1>
                        <span class="link_title">
                           <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706">spring+springmvc+mybatis(下)</a>
                        </span>
                    </h1>
                    <div class="article_description">spring+Mybatis+springMVC的第二部分</div>
                </div>
                <div class="article_manage">
                    <span class="link_postdate">2017-05-09 09:28:13</span>
                    <span class="link_view" title="阅读次数">
                        <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706" title="阅读次数">阅读</a>(744)
                     </span>
                    <span class="link_comments" title="评论次数">
                        <a href="https://blog.csdn.net/sui_feng_piao_guo/article/details/71438706#comments" title="评论次数" onclick="_gaq.push(['_trackEvent','function', 'onclick', 'blog_articles_pinglun'])">评论</a>(0)
                    </span>
                    <span class="link_edit"><a href="https://mp.csdn.net/postedit/71438706" title="编辑">编辑</a></span>
                    <span class="link_edit"><a href="javascript:void(0);" onclick="javascript:topArticle(71438706);return false;" title="置顶">置顶</a></span>
                    <span class="link_delete"><a href="javascript:void(0);" onclick="javascript:deleteArticle(71438706);return false;" title="删除">删除</a></span>
                </div>
                <div class="clear"></div>
            </div>

            <nav aria-label="Page navigation" class="page-nav-outer" id="PageNavId"></nav>
        </div>
    </div>
</body>

<script type="text/javascript">
//    <!-- 启动轮播 -->
    $(document).ready(function(){
        $(".area").hover(function(){
                    $(this).find(".qq").show(100);}
                ,function(){
                    $(this).find(".qq").hide(100);
                });


        var pageNavObj = null;//用于储存分页对象
        pageNavObj = new PageNavCreate("PageNavId",{
            pageCount:30,
            currentPage:1,
            perPageNum:5,
        });
        pageNavObj.afterClick(pageNavCallBack);

        function pageNavCallBack(clickPage){
            pageNavObj = new PageNavCreate("PageNavId",{
                pageCount:30,
                currentPage:clickPage,
                perPageNum:5,
            });
            pageNavObj.afterClick(pageNavCallBack);
        }
    });


</script>
</html>