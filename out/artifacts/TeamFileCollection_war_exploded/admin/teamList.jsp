<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="../dist/js/jquery-3.2.1.js"></script>
    <script>
        function del() {
            var msg = "您真的确定要删除吗？";
            if (confirm(msg) == true) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>
<body>
<table border="1" style="border-collapse: collapse" width="50%" align="center" >
    <thead align="center">
    <td>团队头像</td>
    <td>团队账号</td>
    <td>团队名称</td>
    <td>团队密码</td>
    <td>团队简介</td>
    <td>操作</td>

    </thead>
    <tbody>
    <c:forEach items="${team}" var="user">
        <tr>
            <td>${team.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.gender}</td>
            <td>${user.area}</td>
            <td><a href="${pageContext.request.contextPath}/update.do?id=${user.id}">修改</a></td>
            <td><a href="${pageContext.request.contextPath}/delete.do?id=${user.id}"
                   onclick="javascript:return del();">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8" align="center">
            <a href="queryall.do?page=1">首页</a>
            <c:if test="${page gt 1}">
                <a href="queryall.do?page=${page -1 }">上一页</a>
            </c:if>
            <a href="queryall.do?page=${page+1}">下一页</a>
            <a href="queryall.do?page=${totalpages}">末页</a>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
