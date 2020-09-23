<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/9/14
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
    String path=request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        function checknum(obj) {
            let num = obj;
            let checknumResult = document.getElementById("checknumResult");
            if (num.trim().length == 0) {
                checknumResult.innerHTML = "用户编号不能为空";
                obj.focus();
            } else {
                checknumExists(num);
            }
        }
        function checkname(obj) {
            let name = obj;
            let checknameResult = document.getElementById("checknameResult");
            if (name.trim().length == 0) {
                checknameResult.innerHTML = "名称不能为空";
                obj.focus();
            } else {
                checknameResult.innerHTML="";
            }
        }
        function checknumExists(num) {
            let prompt = document.getElementById("checknumResult");
            $.post("existMemberNumber.do", {num: num}, function (data) {
                if (data == 'true') {
                    prompt.innerHTML = "<font style='color: red'>该账号太受欢迎，请重新输入</font>";
                    document.getElementById("submit").disabled = true;
                } else {
                    prompt.innerHTML = "<font style='color: greenyellow'></font>";
                    document.getElementById("submit").disabled = false;
                }
            });
        }

        function insertMember() {
            if(confirm("确定要申请加入该团队吗？")){
                location.href="${pageContext.request.contextPath}/add1.do";
            }
        }

        // existMemberNumber.do

    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/add1.do" method="post">
    成员编号：<input type="text" id="num" name="num" onblur="checknum(this.value)"/>
    <span id="checknumResult" style="color: red"></span><br/>
    名&nbsp;&nbsp;称：<input type="text" id="name" name="name" onblur="checkname(this.value)"/>
    <span id="checknameResult" style="color: red"></span><br/>
<%--    <input type="hidden" id="teamId" value="${}"/>--%>
    <input type="submit" name="apply" value="申请" id="submit" <%--onclick="insertMember()"--%>>
</form>
</body>
</html>
