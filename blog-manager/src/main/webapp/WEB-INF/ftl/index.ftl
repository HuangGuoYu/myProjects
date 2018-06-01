
<html>
<head>
    <meta charset="UTF-8">

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/index.css"/>
    <link rel="stylesheet" href="/resource/layui-1/css/layui.css">
    <script src="/resource/layui/layui.js" type="text/javascript"></script>
    <script type=text/javascript src="/resource/img_turn/js/js.js"></script>
    <style>
        .box {
            display: box;
            display: -moz-box;
            display: -webkit-box;
        }

        .box-ver {
            box-orient: vertical;
            -moz-box-orient: vertical;
            -webkit-box-orient: vertical;
        }

        .flex1 {
            flex: 1;
            -webkit-box-flex: 1;
            -moz-box-flex: 1;
        }

        .flex2 {
            flex: 2;
            -webkit-box-flex: 2;
            -moz-box-flex: 2;
        }

        .flex5 {
            flex: 15;
            -webkit-box-flex: 15;
            -moz-box-flex: 15;
        }
    </style>
    <title>后端管理</title>
</head>
<body>
<div >
    <div id="top">
        <ul class="layui-nav" style="text-align: right">
            <li class="layui-nav-item">
                欢迎您：
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><img src="http://t.cn/RCzsdCq" class="layui-nav-img"></a>
                <dl class="layui-nav-child">
                    <dd><a href="/admin/logout">退出登录</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div id="bottom" class="box" style="height: 90%">
        <div id="bottom-left" style="width: 200px">
            <ul class="layui-nav layui-nav-tree  layui-nav-side" lay-filter="demo"
                style="margin-right: 10px;margin-top: 60px; ">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">C端用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:open('/article/articleManager','文章管理');">文章管理</a></dd>
                        <dd><a href="javascript:open('/userManager/commentManager','评论管理')">评论管理</a></dd>
                        <dd><a href="javascript:open('/userManager/msgManager','聊天管理')">聊天管理</a></dd>
                        <dd><a href="javascript:open('/userManager/list','用户管理')">用户管理</a></dd>
                        <dd><a href="javascript:open('/userManager/userIncomePage','用户提现管理')">用户提现管理</a></dd>
                    </dl>
                </li>
                <#if isAdmin = 1>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">系统人员管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:open('/sysUser/listPage', '系统人员管理');" onclick="">系统人员管理</a></dd>
                        </dl>
                    </li>
                </#if>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">统计分析</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:open('/static/userRegStatic', '用户注册统计');" onclick="">用户注册统计</a></dd>
                        <dd><a href="javascript:open('/static/articleStatic', '文章统计');" onclick="">文章统计</a></dd>
                        <dd><a href="javascript:open('/static/expensesStatic', '平台支出');" onclick="">平台支出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">系统内容管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:open('/file/imgUpload', '广告管理');" onclick="">添加广告</a></dd>
                        <dd><a href="javascript:open('/file/adManager', '广告管理');" onclick="">广告管理</a></dd>
                        <dd><a href="javascript:open('/file/words', '广告管理');" onclick="">词汇管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div id="bottom-right" class="flex1" >
            <div id="contentTitle" style="border-left: 3px solid red;margin-bottom: 20px;font-size: 20px;margin-left: 10px;margin-top: 10px;">dabshudbsa</div>
            <iframe style="width: 100%;height: 100%;" src="/userManager/list" id="iframe"></iframe>
        </div>
    </div>
</div>
</body>
<script>
    $("#contentTitle").text("用户管理");
    function open(url, name) {
        $("#iframe").attr("src", url);
        $("#contentTitle").text(name);
    }
</script>
</html>