<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- saved from url=(0076)https://tower.im/members/e43457750de8deb93cb2b9a276c02112/todos/uncompleted/ -->
<html lang="zh-cmn-Hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>团队成员 - TFC</title>
    <%@include file="_headLayout.jsp"%>
    <script>
        function del1(memberId) {
            if(confirm("是否删除该成员？")){
                location.href="${pageContext.request.contextPath}/deleteMember.fuc?memberId="+memberId;
            }

        }
    </script>
</head>
<body class="">

    <div class="wrapper">

        <%@include file="_myInfoLayout.jsp"%>


        <div class="container workspace simple-stack simple-stack-transition">
            <div class="page page-root simple-pjax">
                <div class="page-inner page-member" id="page-member">

                    <%@include file="_teamInfoLayout.jsp"%>
                    <script>
                        $("#page3").attr("class","active");
                    </script>


                    <div class="member-section member-todos">
                        <tr-member-todo-box class="member-todo-box ">
                            <tr-todos-group>
                                <div class="group-todolists">
                                    <tr-todolist-plus>

                                        <c:forEach var="item" items="${list}">
                                            <tr-todo-item-plus>
                                                <div class="todo-content-container">
                                                    <div class="todo-actions">
                                                        <a class="del action" title="删除" href="javascript:del1(${item.id});">
                                                            <i class="twr twr-trash"></i>
                                                        </a>
                                                    </div>
                                                    <div class="todo-img">
                                                        <img class="avatar" alt="" src="${item.iconUrl}">
                                                    </div>
                                                    <div class="todo-content">
                                                        <div class="todo-infos">
                                                            <div class="title-container">
                                                            <span class="title-content">
                                                                <a class="todo-rest" href="javascript:;">${item.number}</a>
                                                            </span>
                                                            </div>
                                                        </div>
                                                        <div class="todo-infos">
                                                        <span class="label todo-project">
                                                                ${item.name}
                                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </tr-todo-item-plus>
                                        </c:forEach>


                                    </tr-todolist-plus>
                                </div>
                            </tr-todos-group>
                            <div class="init-message">
                                没有指派给你的任务
                            </div>
                        </tr-member-todo-box>

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
<%@include file="_footLayout.jsp"%>
</html>