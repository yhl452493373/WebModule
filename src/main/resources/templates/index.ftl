<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<p>
${message}
</p>
<@shiro.user>
<p>登录成功</p>
<p>如果你看到此消息,说明你已经登录</p>
<p><a href="${springMacroRequestContext.contextPath}/logout">退出登录</a></p>
</@shiro.user>
</body>
</html>