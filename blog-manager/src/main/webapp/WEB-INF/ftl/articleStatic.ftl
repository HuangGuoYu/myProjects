<html>
<head>
    <meta charset="UTF-8">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resource/js/echarts.js"></script>
    <title>用户注册分析</title>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="post_article" style="width: 800px;height:400px;"></div>
<hr />
<div id="cert_article" style="width: 800px;height:400px;"></div>
<hr />
<div id="error_article" style="width: 800px;height:400px;"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var post_article = echarts.init(document.getElementById('post_article'));
    var cert_article = echarts.init(document.getElementById('cert_article'));
    var error_article = echarts.init(document.getElementById('error_article'));

    var post_option = {
        title: {
            text: '用户近一周注册分析'
        },
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };
    var cert_option = {
        title: {
            text: '用户近一周注册分析'
        },
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };
    var error_option = {
        title: {
            text: '用户近一周注册分析'
        },
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };

    $.get("/static/articlePostStatic",function (res) {
        if (res.code == 200) {
            post_option.title.text = "文章发布总数";
            post_option.xAxis.data = res.data.xs;
            post_option.series[0].data = res.data.xds;
            post_article.setOption(post_option);
        }
    });

    $.get("/static/articleCertStatic",function (res) {
        if (res.code == 200) {
            cert_option.title.text = "文章审核通过数"
            cert_option.xAxis.data = res.data.xs;
            cert_option.series[0].data = res.data.xds;
            cert_article.setOption(cert_option);
        }
    });

    $.get("/static/articleDeafStatic",function (res) {
        if (res.code == 200) {
            error_option.title.text = "文章审核失败总数"
            error_option.xAxis.data = res.data.xs;
            error_option.series[0].data = res.data.xds;
            error_article.setOption(error_option);
        }
    });
</script>
</html>