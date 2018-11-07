<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<@shiro.user>
    <a href="${springMacroRequestContext.contextPath}/logout">退出登录</a>
</@shiro.user>
<@shiro.guest>
    <form action="${springMacroRequestContext.contextPath}/login" method="post">
        <button type="submit">游客登录</button>
    </form>
</@shiro.guest>
</body>
</html>