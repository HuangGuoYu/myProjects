<html>
<head>
    <meta charset="UTF-8">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resource/js/echarts.js"></script>
    <title>用户注册分析</title>
</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 800px;height:400px;"></div>
    <hr />
    <div id="line_chat" style="width: 800px;height:400px;"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    var line_chat = echarts.init(document.getElementById('line_chat'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户近一周注册分析'
        },
        tooltip: {},
        legend: {
            data:['注册数']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '注册数',
            type: 'bar',
            data: []
        }]
    };

    var line_option = {
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

    $.get("/static/userRegStaticData",function (res) {
       if (res.code == 200) {
           option.xAxis.data = res.data.xs;
           option.series[0].data = res.data.xds;
           line_option.xAxis.data = res.data.xs;
           line_option.series[0].data = res.data.xds;
           console.log(option);
           // 使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);
           line_chat.setOption(line_option);
       }
    });
</script>
</html>