<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
    <script src="/resource/layui/layui.js" type="text/javascript"></script>
    <style type="text/css">
        body{
            background: url("/resource/imgs/reg_backgroud.png") no-repeat;
            background-size:100% 100%;
            background-attachment: fixed;
            text-align: center;
        }

        .registerTable{
            text-align: center;
            border-radius: 20px;
            width: 400px;
            height: 450px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%,-50%);
            background-color: #737383;
            padding: 20px;
        }
        a:hover{
            color: blue;
        }


    </style>
</head>
<body>
       <div class="registerTable">
            <h1>注册</h1>
           <div class="layui-form-item" style="margin-top: 30px">
               <label class="layui-form-label">邮箱</label>
               <div class="layui-input-block">
                   <input id="account" type="text" name="account" required  lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label">密码</label>
               <div class="layui-input-block">
                   <input id="pwd" type="password" name="pwd" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label">确认密码</label>
               <div class="layui-input-block">
                   <input id="ackPwd" type="password" name="ackPwd" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label">博客名称</label>
               <div class="layui-input-block">
                   <input id="blogName" type="text" name="blogName" required  lay-verify="required" placeholder="请输入博客名称" autocomplete="off" class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label">验证码</label>
               <div class="layui-input-inline">
                   <input id="verifyCode" type="text" name="verifyCode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
               </div>
               <div class="layui-form-mid layui-word-aux"><a id="getVerifyCode" href="javascript:void(0);">获取验证码</a></div>
           </div>
           <div class="layui-form-item" style="margin-top: 50px;">
               <div class="layui-input-block">
                   <button class="layui-btn" lay-submit lay-filter="formDemo" id="submit">立即提交</button>
                   <button type="reset" class="layui-btn layui-btn-primary" id="reset">重置</button>
               </div>
           </div>
       </div>
</body>
<script>
    var layer;
    layui.use('layer', function(){
        layer = layui.layer;
    });

    $("#reset").on("click",function () {
       $("input").val("");
    });
    var data = {}
    $("#submit").on("click", function () {
        $("input").each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        $.post("/user/register", data, function (res) {
            console.log(res);
            if (res.code != 200) {
                layer.alert(res.msg)
            } else {
                layer.alert(res.msg)
                setTimeout(window.location.href = res.url, 1500);
            }
        })
    });

    $("#getVerifyCode").on("click",function () {
        var account = $("#account").val();
        if (account == undefined || !(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(account))) {
            layer.alert("邮箱格式错误");
            return;
        }
        $.get("/user/sendVerifyCode", {"account":account}, function (res) {
            layer.alert(res.msg);
        });
    })

</script>
</html>