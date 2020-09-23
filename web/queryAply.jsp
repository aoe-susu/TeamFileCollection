<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/9/21
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript"></script>
    <script>
        function add(id,number,name,teamId) {
            if(confirm("是否同意入团申请？")){
                <%--"${pageContext.request.contextPath}/add.do?id="+id+"&number="+number+"&name="+name+"&teamId="+teamId;--%>
                console.log("${pageContext.request.contextPath}/add.do?id="+id+"&number="+number+"&name="+name+"&teamId="+teamId);
                location.href= "${pageContext.request.contextPath}/add.do?id="+id+"&number="+number+"&name="+name+"&teamId="+teamId;
            }
        }
        function del1(id) {
            if(confirm("是否拒绝申请？")){
                location.href="${pageContext.request.contextPath}/delete2.do?id="+id;
            }

        }
    </script>
</head>
<body>
<form action="" id="form" method="post">
    <div class="result-content">
        <table class="result-tab" width="60%">
            <tr>
                <td>成员ID</td>
                <td>成员编号</td>
                <td>成员名</td>
                <td>团队ID</td>

                <td>操作一</td>
                <td>操作二</td>
            </tr>
            <c:forEach var="t" items="${teamMember}">
                <tr>
                    <td>${t.id }</td>
                    <td>${t.number }</td>
                    <td>${t.name }</td>
                    <td>${t.teamId }</td>

                    <td><input   type="button" value="同意" onclick="add(${t.id},'${t.number}','${t.name}',${t.teamId})" /></td>
                    <td><input   type="button" value="拒绝" onclick="del1(${t.id})" ></td>
                </tr>
            </c:forEach>

        </table>
    </div>

</form>
</body>
</html>
