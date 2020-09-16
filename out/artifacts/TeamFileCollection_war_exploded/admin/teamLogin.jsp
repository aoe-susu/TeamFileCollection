<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="../js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="../js/team.js" type="text/javascript"></script>
</head>
<body>

<%--<input type="button" value="进入团队" onclick="login()">--%>

<form action="${pageContext.request.contextPath}/login.do" method="post" <%--style="display: none" --%>id="form">
    用户名：<input type="text" name="account" id="account-1" onblur="checkAccountNull()"/>
    <div id="account-2" style="display: inline"></div>
    <br/>

    密码：<input type="password" name="password" id="pwd-1" onblur="checkPwdNull()"/>
    <div id="pwd-2" style="display: inline"></div>
    <br/>

    验证码：<input type="text" name="varifycode" placeholder="请输入验证码" id="varifcode-1" onblur="checkVarifycodeNull()"/>
    <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCode.do" title="看不清点击刷新" id="vcode"/></a>
    <div id="varifcode-2" style="display: inline"></div>
    <br/>
    <button type="submit" onsubmit="checkNotNull()">登录</button>
    <button type="reset">重置</button>
</form>

<%--显示出错提示信息--%>
<div>
    <strong>${teamLogin_msg}</strong>
</div>
</body>
</html>
