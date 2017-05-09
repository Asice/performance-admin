<#include "/comm/taglibs.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>INSPINIA | Login</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/css/animate.css" rel="stylesheet">
    <link href="${ctx}/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="loginColumns animated fadeInDown">
       
    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">For</h1>

            </div>
            <h3>欢迎登录男人帮管理后台</h3>
            <br/>
            <br/>
            <form class="m-t" method="post" action="${BASE_PATH}/login/validate">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="账号" required="true">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required="true">
                </div>
                <div class="form-group">
                	 <input type="checkbox" id="remember" checked="checked" value="1" name="remember"/>
                	 <label for="remember">记住密码</label>
                </div>
                <div class="form-group">
                	<span id="msg" style="color:red;"></span>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
            </form>
            <strong>Copyright</strong>  Company © 2017
        </div>
    </div>
 </div>
</body>
<script type="text/javascript">
	var msg = "${msg}";
	if (msg) {
		document.getElementById("msg").innerHTML=msg;
	}
</script>
</html>
