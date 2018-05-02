<!
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui-nim/layui.all.js" type="text/javascript"></script>
    <title>文件上传</title>
</head>
<body>
<br />
<br />
<br />
<form enctype="multipart/form-data" method="post" action="/file/img" style="width: 400px;">
    <div class="layui-form-item">
        <label class="layui-form-label">目标地址</label>
        <div class="layui-input-block">
            <input type="text" name="url" required  lay-verify="required" placeholder="请输入广告目标地址" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">广告图片</label>
        <div class="layui-input-block">
            <input type="file" name="file" required  lay-verify="required"  autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
                <input type="text" name="content" required  lay-verify="required" placeholder="请输入不超过十字的描述" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>


</body>
</html>