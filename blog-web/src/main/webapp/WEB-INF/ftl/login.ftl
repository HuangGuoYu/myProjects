<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/layui/css/layui.css">
    <script src="https://cdn.bootcss.com/jquery/1.8.3/jquery.min.js"></script>
    <title>登录</title>
    <style rel="stylesheet" type="text/css">
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
            height: 260px;
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
            <h1 style="font-size: 30px;">登录</h1>
           <div class="layui-form-item" style="margin-top: 30px">
               <label class="layui-form-label">邮箱</label>
               <div class="layui-input-block">
                   <input id="account" type="text" name="account" required  lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input" value="838937070@qq.com">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label">密码/验证码</label>
               <div class="layui-input-inline">
                   <input id="pwd" type="password" name="pwd" required lay-verify="required" placeholder="请输入密码或验证码" autocomplete="off" class="layui-input">
               </div>
               <div class="layui-form-mid layui-word-aux" onclick="queryVeriCode()"><strong style="cursor: pointer;">获取验证码</strong></div>
           </div>
           <div class="layui-form-item" style="margin-top: 50px;">
               <div class="layui-input-block" style="margin-left: 108px;">
                   <button class="layui-btn" lay-submit lay-filter="formDemo" id="submit">密码登录</button>
                   <button class="layui-btn layui-btn-primary" onclick="loginByCode()">验证码登录</button>
                   <a href="/user/registerPage" class="layui-btn">注册</a>
               </div>
           </div>
       </div>
</body>
<script src="/resource/layui-nim/layui.all.js"></script>
<script type="text/javascript">
    console.log(layui)
    var layer;
    layui.use(['layer'], function(){
        layer = layui.layer;
    });
    $("#reset").on("click",function () {
        $("input").val("");
    });
    var data = {}
    //用户登录
    $("#submit").on("click", function () {
        $("input").each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        data["callBack"] = '${callBack}';
        $.post("/user/login", data, function (res) {
            console.log(res);
            if (res.code != 200) {
                layer.alert(res.msg, {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                })
            } else {
                window.location.href = res.url;
            }
        })
    });

    function loginByCode() {
        $("input").each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        data["callBack"] = '${callBack}';
        $.post("/user/loginByCode", data, function (res) {
            console.log(res);
            if (res.code != 200) {
                layer.alert(res.msg, {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                })
            } else {
                window.location.href = res.url;
            }
        })
    }

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

    //获得登录验证码
    function queryVeriCode() {
        var account = $("#account").val();
        $.post("/user/sendLoginVC", {account:account}, function (res) {
            layer.alert(res.msg);
        })
    }

</script>
</html>