<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改基本信息</title>
    <script src="../js/jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/modifyTeamBaseInfo.do" method="post">
    <!--  隐藏域 提交id-->
    <input type="hidden" name="id" value="${team.id}">

    账号：
    <input type="text" id="account" name="account" value="${team.account}" readonly="readonly"/>

    名称：
    <input type="text" id="name" name="name" value="${team.name}"/>

    团队简介：
    <input type="text" id="intro" name="name" value="${team.introduction}"/>

    <input type="submit" value="提交"/>
    <input type="reset" value="重置"/>
    <input type="button" value="返回"/>

</form>
</body>
</html>
