<html>
<head>
    <meta charset="UTF-8">
    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <link rel="stylesheet" href="/resource/css/index.css">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <script src="https://cdn.bootcss.com/jquery/1.9.0/jquery.js"></script>
    <script src="/resource/layui/layui.js" type="text/javascript"></script>

    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <!-- 轮播插件 -->
    <script type=text/javascript src="/resource/img_turn/js/js.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/chat.css">
    <title>Document</title>


</head>

<body style="background: url('/resource/imgs/chat_body_bg.jpg')">

    <div class="qqBox">
        <div class="BoxHead">
            <div class="headImg">
                <img src="/resource/imgs/headimg.jpg">
            </div>
            <div class="internetName">90后大叔</div>
        </div>

        <div class="context clearfloat">
            <div class="conLeft">
                <ul>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li  class="selectBg">
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                    <li>
                        <div class="liLeft"><img src="/resource/imgs/headimg.jpg"></div>
                        <div class="liRight">
                            <span class="intername">前端交流群</span>
                            <span class="infor">厉害了</span>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="conRight">
                <div class="RightHead clearfloat">
                    <div class="headName left">赵鹏</div>
                </div>
                <div class="RightCont">
                    <div class="ChatRecord">查看更多消息</div>
                    <ul class="newsList">
                        <li>
                            <div class="answerHead"><img src="/resource/imgs/headimg.jpg"></div>
                            <div class="answers">
                                    <img src="/resource/imgs/headimg.jpg">
                            </div>
                        </li>
                    </ul>
                </div>

                <div class="inputBox clearfloat" style="border-top: 1px solid #ccc;">
                    <textarea id="dope" style="width: 99%;height: 75px; border: none;outline: none;" name="" rows="" cols=""></textarea>
                    <button class="btn layui-btn-danger right">发送(s)</button>
                </div>
            </div>
        </div>
    </div>


</body>
<script src="/resource/js/template.js"></script>
<script>
    var data= {
        "code": 0 //0表示成功，其它表示失败
        ,"msg": "" //失败信息
        ,"data": {
            //我的信息
            "mine": {
                "username": "纸飞机" //我的昵称
                ,"id": "100000" //我的ID
                ,"status": "online" //在线状态 online：在线、hide：隐身
                ,"sign": "在深邃的编码世界，做一枚轻盈的纸飞机" //我的签名
                ,"avatar": "/resource/imgs/headimg.jpg" //我的头像
            }
            //好友列表
            ,"friend": [{
                "groupname": "前端码屌" //好友分组名
                ,"id": 1 //分组ID
                ,"list": [{ //分组下的好友列表
                    "username": "贤心" //好友昵称
                    ,"id": "100001" //好友ID
                    ,"avatar": "/resource/imgs/headimg.jpg" //好友头像
                    ,"sign": "这些都是测试数据，实际使用请严格按照该格式返回" //好友签名
                    ,"status": "online" //若值为offline代表离线，online或者不填为在线
                }]
            }]

    //群组列表
    ,"group": [{
        "groupname": "前端群" //群组名
        ,"id": "101" //群组ID
        ,"avatar": "/resource/imgs/headimg.jpg" //群组头像
    }]
    }
    };
    layui.use('layim', function(layim){
        //基础配置
        layim.config({
            init: {
                //我的信息
                "mine": {
                    "username": "纸飞机" //我的昵称
                    ,"id": "100000" //我的ID
                    ,"status": "online" //在线状态 online：在线、hide：隐身
                    ,"sign": "在深邃的编码世界，做一枚轻盈的纸飞机" //我的签名
                    ,"avatar": "/resource/imgs/headimg.jpg" //我的头像
                }
                //好友列表
                ,"friend": [{
                    "groupname": "前端码屌" //好友分组名
                    ,"id": 1 //分组ID
                    ,"list": [{ //分组下的好友列表
                        "username": "贤心" //好友昵称
                        ,"id": "100001" //好友ID
                        ,"avatar": "/resource/imgs/headimg.jpg" //好友头像
                        ,"sign": "这些都是测试数据，实际使用请严格按照该格式返回" //好友签名
                        ,"status": "online" //若值为offline代表离线，online或者不填为在线
                    }]
                }]

                //群组列表
                ,"group": [{
                    "groupname": "前端群" //群组名
                    ,"id": "101" //群组ID
                    ,"avatar": "/resource/imgs/headimg.jpg" //群组头像
                }]}//获取主面板列表信息，下文会做进一步介绍
            //获取群员接口（返回的数据格式见下文）
            ,members: {
                url: '' //接口地址（返回的数据格式见下文）
                ,type: 'get' //默认get，一般可不填
                ,data: {} //额外参数
            }
            //上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
            ,uploadImage: {
                url: '' //接口地址
                ,type: 'post' //默认post
            }
            //上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
            ,uploadFile: {
                url: '' //接口地址
                ,type: 'post' //默认post
            }
            //扩展工具栏，下文会做进一步介绍（如果无需扩展，剔除该项即可）
            ,tool: [{
                alias: 'code' //工具别名
                ,title: '代码' //工具名称
                ,icon: '&#xe64e;' //工具图标，参考图标文档
            }]
//            ,msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
//            ,find: layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
//            ,chatLog: layui.cache.dir + 'css/modules/layim/html/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可
        });
    });

</script>
</html>