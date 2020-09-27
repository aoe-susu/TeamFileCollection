<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0076)https://tower.im/members/e43457750de8deb93cb2b9a276c02112/todos/uncompleted/ -->
<html lang="zh-cmn-Hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <title>团队任务 - TFC</title>


    <%@include file="_headLayout.jsp"%>

    <style>

        body {
            background: #F4F7ED;
            background-attachment: fixed;
            background-size: cover;
        }
        .pagediv{
            position:absolute;
            top: 500px;
        }
        .pagination li{
            margin: 20px;
            display: inline;
            font-size: 25px;
        }

        .pagination .btn{
            color: white;
        }
        .pagination .btn:hover{
            color: white;
        }
        .pagination li a{
            color: black;
        }
        .pagination li a:hover{
            color: #51c6cf;
        }

        .btn{
            margin: 0px;
        }
        .todo-content{
            margin-left: 30px;
        }
    </style>

    <script>
        function deleteTask(id) {
            if(confirm("您确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/delCollectionTaskServlet?currentPage=${pb.currentPage}&rows=3&id="+id+"&all=${pageContext.request.getParameter("all")}";
            }
        }
        var success="${success}";
        if(success=="true"){
            alert("上传成功");
        }
        else if(success=="false"){
            alert("上传失败");
        }

        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').focus();
        })

        $(document).ready(function () {
            $("#button").click(function () {
                location.href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?all=true";
            })
        })

        $(document).ready(function () {
            $("#add").click(function () {
                location.href="${pageContext.request.contextPath}/add.jsp?currentPage=${pb.currentPage}&rows=3&all=${pageContext.request.getParameter("all")}";
            })
        })

        $(document).ready(function () {
            $("#deadline").click(function () {
                location.href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?all=false";
            })
        })
    </script>
</head>
<body class="">

<div class="wrapper">

    <%@include file="_myInfoLayout.jsp"%>

    <div class="container workspace simple-stack simple-stack-transition">
        <div class="page page-root simple-pjax">
            <div class="page-inner page-member" id="page-member" >
                <div class="member-section member-todos">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/register.action"
                          method="post">
                        <div class="member-info">
                            <div class="member-info-content">
                                <div class="member-card">
                                    <h1>
                                        <span id="teamName" class="name">创建团队</span>
                                    </h1>
                                </div>
                            </div>
                        </div>
                        <br/><br/>
                        <tr-member-todo-box class="member-todo-box " style="margin-left: 50px;">
                            <tr-todos-group>
                                <div class="group-todolists">
                                    <tr-todolist-plus >
                                        <tr-todo-item-plus>
                                            <h3>团队账号信息</h3>
                                            <div class="todo-content-container">
                                                <label>团队账号</label><br/>
                                                <div class="todo-content">
                                                    <input class="form-control" type="text" name="account" id="account" onblur="checkAccountExists(this.value)"/>

                                                </div>
<%--                                                <label  style="text-align:left;" id="mess" style="display: inline;flex: 2;"></label>--%>
                                                <strong style="color: #ff0c10; font-size: 20px;flex: 2;">${pageContext.request.getParameter("register_msg")}</strong>
                                            </div>
                                            <div class="todo-content-container">
                                                <label>团队密码</label><br/>
                                                <div class="todo-content">
                                                    <input  type="password" name="password" id="pwd"/>

                                                </div>

                                            </div>
                                            <div class="todo-content-container">
                                                <label>确认密码</label><br/>
                                                <div class="todo-content">
                                                    <input type="password" name="repwd" id="repwd" onkeyup="checkpassword()"/>
                                                </div>
                                                <label style="text-align:left;flex: 2;" id="pa" style="display: inline"></label>
                                            </div>
                                        </tr-todo-item-plus>
                                        <tr-todo-item-plus>
                                            <h3>团队基本信息</h3>
                                            <div class="todo-content-container">
                                                <label>团队名称</label><br/>
                                                <div class="todo-content">
                                                    <input type="text" name="name" id="name"/>
                                                </div>
                                            </div>
                                            <div class="todo-content-container">
                                                <label>团队简介</label><br/>
                                                <div class="todo-content">
                                                 <textarea style="height: 100px;" id="intro"
                                                           name="introduction"></textarea>
                                                </div>
                                            </div>
                                        </tr-todo-item-plus>
                                        <tr-todo-item-plus>
                                            <h3>团队验证信息</h3>
                                            <div class="todo-content-container">
                                                <label>验证码 &nbsp;&nbsp; </label><br/>
                                                <div class="todo-content">
                                                    <input type="text" name="varifycode" id="varifycode"
                                                           placeholder="请输入验证码"/>

                                                </div>
                                                <div class="todo-content" style="flex: 2;">
                                                    <a href="javascript:refreshCode()">
                                                        <img src="${pageContext.request.contextPath}/checkCode.action" title="看不清点击刷新" id="vcode"/>
                                                    </a>
                                                </div>
                                            </div>
                                        </tr-todo-item-plus>
                                    </tr-todolist-plus>
                                </div>
                            </tr-todos-group>
                        </tr-member-todo-box>

                        <dic class="member-section">
                            <button class="tr-button mdc-button mdc-button--outlined mdc-button--dense"  type="submit" id="submit">
                                <span class="mdc-button__label">创建</span>
                            </button>
                            <button class="tr-button mdc-button mdc-button--outlined mdc-button--dense" type="reset">
                                <span class="mdc-button__label">重置 </span>
                            </button>
                        </dic>
                    </form>

                </div>
            </div>
        </div>
    </div>
    <div class="footer light ">
        © 彩程设计
        | 团队ID: 865475
    </div>

</div>

</body>


<%@include file="_footLayout.jsp"%>>


</html>
