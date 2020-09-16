<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="js/jquery-3.2.1.js"></script>
  </head>
  <body>
  <h2>欢迎来到团队主页</h2>
  <input type = "button" value = "修改基本信息" onclick = "window.location.href = '${pageContext.request.contextPath}/getTeamById.do?id=${team.id}'">
  </body>
</html>
