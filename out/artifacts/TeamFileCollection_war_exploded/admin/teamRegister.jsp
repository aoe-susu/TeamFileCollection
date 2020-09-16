<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册</title>
    <script src="../js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="../js/team.js" type="text/javascript"></script>
</head>
<body>

<form action="${pageContext.request.contextPath}/register.do" method="post">
    团队账号：<input type="text" name="account" onblur="checkAccountExists(this.value)"  />
    <div id="mess" style="display: inline"></div><br/>

    团队密码：<input type="password" name="password" id="pwd"/><br/>
    确认密码：<input type="password" name="repwd" id="repwd" onkeyup="checkpassword()">
    <div id="pa" style="display: inline"></div><br/>

    团队名称<input type="text" name="name" onblur="checkNotNull()"/><br/>
    团队简介:<textarea cols="20" rows="2" name="introduction" ></textarea><br/>
    验证码：<input type="text" name="varifycode" placeholder="请输入验证码" />
    <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCode.do" title="看不清点击刷新" id="vcode"/></a><br/>
    <input type="submit" value="注册"/>
    <input type="reset" value="重置"/>
</form>

<%--显示验证码错误信息--%>
<div>
<strong>${register_msg}</strong>
</div>

</body>
</html>
