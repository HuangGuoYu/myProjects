<html>
<head>
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=0.9, maximum-scale=0.9, minimum-scale=0.9, user-scalable=no" />
<script src="/resource/js/jquery_1.9.0_jquery.js" type="text/javascript"></script>
<script src="/resource/layui-nim/layui.all.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/login.css" />
</head>
<body>
<div class="banner">
	<div class="inner"></div>
</div>

<div class="input-box">
	<form action="#" method="post" onsubmit="return false;">
		<input id="balcb" type="hidden" value="" />
		<div class="input-f"><input id="uname" class="uname" type="text" name="username" maxlength="20" /><span class="n"></span></div>
		<span style="color: red;" id="idResult"></span>
		<div class="input-f"><input id="pwd" class="uname" type="password" maxlength="20" /><span class="p"></span></div>
		<input id="login" type="submit" value="登录" class="btn" />
	</form>
</div>
		
</body>
<script>
	var layer = layui.layer;
	$(".btn[type=submit]").on("click", function () {
		var $uname = $("#uname");
		var $pwd = $("#pwd");
		data = {};
		data.account = $uname.val();
		data.pwd = $pwd.val();
		if (data.account.length == 0 || data.pwd.length == 0) {
			layer.alert("用户名和密码不能为空");
		}
		$.post("/admin/loginDeal", data, function (res) {
			if (res.code == 200) {
			    window.location.href = res.url;
			} else {
			    layer.msg(res.msg);
			}
        })
    })
</script>
</html>