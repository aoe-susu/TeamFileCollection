<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看任务</title>
    <style>
        table tr td{
            width: 250px;
            text-align: center;
            margin: auto;
        }
    </style>
</head>
<body>
<fieldset>
    <legend>人物列表</legend>
    <table>
        <tr>
        <td>类别</td>
        <td>描述</td>
        <td>团队</td>
        <td>截至时间</td>
        </tr>

        <c:forEach items="${list}" var="list">
            <tr>
                <th>${list.title}</th>
                <td>${list.content}</td>
                <td>${list.teamId}</td>
                <td>${list.deadline}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/handIn.do?id=${list.id}" enctype="multipart/form-data" method="post">
                        <input type="file" name="handFile" value="选择文件" style="width: 170px"> <button type="submit" value="提交">提交</button>
                    </form>
                </td>
<%--
                <td><a href="${pageContext.request.contextPath}/handIn.do?no=${list.teamId}">交作业</a></td>
--%>
                <%--<td><a href="${pageContext.request.contextPath}/del.do?no=${list.no}">删除</a></td>--%>
                    <%--<td><a href="${pageContext.request.contextPath}/before.action?no=${users.no}">上一页</a></td>
                    <td><a href="${pageContext.request.contextPath}/next.action?no=${users.no}">下一页</a></td>--%>
            </tr>
        </c:forEach>
        <br><br><a href="${pageContext.request.contextPath}/showAllStu.do" >显示成员列表</a>   &nbsp;&nbsp;&nbsp;${message}
    </table>
</fieldset>
</body>
</html>
