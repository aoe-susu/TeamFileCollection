<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/9/16
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <title>首页</title>
    <!-- 设置文档编码 -->
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <!--  -->
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="js/jquery-3.2.1.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="js/bootstrap.min.js"></script>
    <!-- CSS样式 -->
    <style type="text/css">
        .container div{
            text-align:center;
        }
    </style>


    <script>
        function checknum(obj) {
            let num = obj;
            let checknumResult = document.getElementById("checknumResult");
            if (num.trim().length == 0) {
                checknumResult.innerHTML = "用户编号不能为空";
                obj.focus();
            } else {
                checknumResult.innerHTML = "";
                //checknumExists(num);
            }
        }
        function checkPwd(obj) {
            let pwd = obj;
            let checkPwdResult = document.getElementById("checkPwdResult");
            if (pwd.trim().length == 0) {
                checkPwdResult.innerHTML = "密码不能为空";
                obj.focus();
            } else {
                checkPwdResult.innerHTML = "";
                //checkPwdExist(pwd);
            }
        }
       /* function checknumExists(num) {
            let prompt = document.getElementById("checknumResult");
            $.post("existMemberNumber.do", {num: num}, function (data) {
                if (data == 'true') {
                    prompt.innerHTML = "<font style='color: greenyellow'></font>";
                    
                } else {
                    prompt.innerHTML = "<font style='color: red'>没有该账号！</font>";
                    document.getElementById("login").disabled = true;
                
                }
            });
        }
        function checkPwdExist(pwd) {
            let prompt = document.getElementById("checkPwdResult");
            $.post("existTeamPassword.do", {pwd: pwd}, function (data) {
                if (data == 'true') {
                    prompt.innerHTML = "<font style='color: greenyellow'></font>";

                } else {
                    prompt.innerHTML = "<font style='color: red'>密码错误！</font>";
                    document.getElementById("login").disabled = true;

                }
            });
        }*/
        function login(){

        }

        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').focus()
        })
    </script>
</head>
<body>
<h2>登陆</h2>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    团员登陆
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/getMemberByPwdAndNum.do" method="post">
                    成员编号：<input type="text" id="num" name="num" onblur="checknum(this.value)"/><span id="checknumResult" style="color: red"></span><br/>
                    团队密码：<input type="password" id="pwd" name="pwd" onblur="checkPwd(this.value)"/><span id="checkPwdResult" style="color: red"></span><br/>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" name="login" <%--onclick="login()"--%>>登陆</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>



</body>
</html>
