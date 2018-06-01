<html>
<head>
    <meta charset="UTF-8">
    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <link rel="stylesheet" href="/resource/css/index.css">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <script src="/resource/js/jquery_1.9.0_jquery.js"></script>
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
</body>
<script src="/resource/js/template.js"></script>
<script>

    layui.use('layim', function(layim){

        var socket = new WebSocket('ws://localhost:8080/myHandler');

        //连接成功时触发
        socket.onopen = function(){
            socket.send('XXX连接成功');
            $.get("/chat/sendMessageForIsNotLook",function (res) {

            });
        };

        //发送消息
        layim.on('sendMessage', function(res){
            var mine = res.mine;
            var to = res.to;
            $.post("/article/checkContent", {content:mine.content}, function (res) {
                if (res.code == 200) {
                    $.post("/chat/sendMessage", {content:mine.content, fromUser:mine.id, toUser:to.id}, function (res) {

                    });
                } else {
                    layer.alert(res.data + ";此消息发送失败");
                }
            })

        });


        //监听收到的消息
        socket.onmessage = function(res){
            //res为接受到的值，如 {"emit": "messageName", "data": {}}
            //emit即为发出的事件名，用于区分不同的消息
            console.log(res, "receive");
            data = JSON.parse(res.data);
            console.log(data)
            layim.getMessage(data);
        };
        //基础配置
        layim.config({
            init: {//获取主面板列表信息，下文会做进一步介绍
                url: '/chat/friendList' //接口地址（返回的数据格式见下文）
                ,type: 'get' //默认get，一般可不填
                ,data: {} //额外参数
            }
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