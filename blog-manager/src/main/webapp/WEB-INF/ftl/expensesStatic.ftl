<html>
<head>
    <meta charset="UTF-8">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resource/js/echarts.js"></script>
    <title>用户注册分析</title>
</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="line_chat" style="width: 800px;height:400px;"></div>

</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var line_chat = echarts.init(document.getElementById('line_chat'));

    var line_option = {
        title: {
            text: '平台支出分析'
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

    $.get("/static/expensesData",function (res) {
       if (res.code == 200) {
           line_option.xAxis.data = res.data.xs;
           line_option.series[0].data = res.data.xds;
           line_chat.setOption(line_option);
       }
    });

</script>
</html>