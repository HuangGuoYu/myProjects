<html>
<head>
    <meta charset="UTF-8">
    <LINK rel=stylesheet type=text/css href="/resource/img_turn/css/css.css">
    <link rel="stylesheet" href="/resource/css/index.css">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <link rel="stylesheet" href="/resource/css/reset.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <script type=text/javascript src="/resource/js/jquery-3.1.1.min.js"></script>
    <script src="/resource/layui/layui.all.js" type="text/javascript"></script>

    <script src="/resource/pageNavigator/js/pageNav.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/resource/pageNavigator/css/pageNav.css" />
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/resource/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/resource/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <!-- 轮播插件 -->
    <script type=text/javascript src="/resource/img_turn/js/js.js"></script>
    <title>Document</title>
</head>

<body>

</body>
<script src="/resource/js/template.js"></script>
<script>
    $(function() {
        console.log("abc");
            var ws = new WebSocket("ws://localhost:8080/myHandler")
            ws.onopen = function () {
                console.log("onpen");
                ws.send("{}");
            }
            ws.onclose = function () {
                console.log("onclose");
            }

            ws.onmessage = function (msg) {
                console.log(msg.data);
            }
    })
</script>
</html>