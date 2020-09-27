<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!-- saved from url=(0076)https://tower.im/members/e43457750de8deb93cb2b9a276c02112/todos/uncompleted/ -->

<html lang="zh-cmn-Hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>任务提交详情 - TFC</title>
    <%@include file="_headLayout.jsp"%>
</head>
<body class="">

    <div class="wrapper">

        <%@include file="_myInfoLayout.jsp"%>

        <div class="container workspace simple-stack simple-stack-transition"  >
            <div class="page page-root simple-pjax">
                <div class="page-inner page-member" id="page-member">

                    <%@include file="_teamInfoLayout.jsp"%>
                    <script>
                        $("#page5").attr("class","active");
                    </script>

                    <div class="member-section member-todos">
                        <tr-member-todo-toolbar>
                            <span>第一次实验报告</span>
                            <span>（${upCount}/${totalCount}）</span>
                            <div class="link-actions more">
                                <a href="${pageContext.request.contextPath}/downAll.do?taskId=${pageContext.request.getParameter("taskId")}">压缩全部任务文件并下载</a>
                                <a href="${pageContext.request.contextPath}/showAllStu.do?taskId=${pageContext.request.getParameter("taskId")}">查看全部</a>
                                <a href="${pageContext.request.contextPath}/showNotHand.do?taskId=${pageContext.request.getParameter("taskId")}">查看未提交</a>
                            </div>
                        </tr-member-todo-toolbar>
                        <c:if test="${choose == 1}">
                        <c:forEach items="${tasks}" var="tasks">
                        <tr-member-todo-box class="member-todo-box ">
                            <tr-todos-group>
                                <div class="group-todolists">
                                    <tr-todolist-plus>
                                        <tr-todo-item-plus>
                                            <div class="todo-content-container">
                                                <c:if test="${tasks.isNot == 1}">
                                                    <div class="todo-actions">
                                                        <a class="action edit" title="下载文件" target="_blank" href="${pageContext.request.contextPath}/down.do?memberId=${tasks.memberId}&taskId=${tasks.taskId}">
                                                            <div class="action"><i class="twr twr-download"></i></div>
                                                        </a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${tasks.isNot == 0}">

                                                </c:if>
                                                <div class="todo-img">
                                                    <img class="avatar" alt="" src="./img/1.gif">
                                                </div>
                                                <div class="todo-content">
                                                    <div class="todo-infos">
                                                        <div class="title-container">
                                                            <span class="title-content">
                                                                <a class="todo-rest" href="javascript:;">${tasks.number}</a>
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div class="todo-infos">
                                                        <span class="label todo-project">
                                                            <a class="todo-rest" href="javascript:;">${tasks.name}</a>
                                                        </span>
                                                    </div>
                                                </div>
                                                   <c:if test="${tasks.isNot == 1}">
                                                       <div class="todo-due-date">
                                                           <span>提交时间：</span><span><fmt:formatDate type="time" value="${tasks.date}" pattern="yyyy-MM-dd" /></span>
                                                           &nbsp;&nbsp;
                                                           <span style="color:green;"><i class="twr twr-checkitem-checked-outline"></i></span>
                                                       </div>
                                                   </c:if>
                                                   <c:if test="${tasks.isNot == 0}">
                                                       <div class="todo-due-date">
                                                           <span>${tasks.up}</span>
                                                           &nbsp;&nbsp;
                                                           <span style="color:red;"><i class="twr twr-help"></i></span>
                                                       </div>
                                                   </c:if>

                                            </div>
                                        </tr-todo-item-plus>

                                    </tr-todolist-plus>
                                </div>
                            </tr-todos-group>
                            <div class="init-message">
                                没有指派给你的任务
                            </div>
                        </tr-member-todo-box>
                        </c:forEach>
                        </c:if>
                        <c:if test="${choose == 0}">
                            <c:forEach items="${tasks}" var="tasks">
                                <c:if test="${tasks.isNot == 0}">
                                <tr-member-todo-box class="member-todo-box ">
                                    <tr-todos-group>
                                        <div class="group-todolists">
                                            <tr-todolist-plus>
                                                <tr-todo-item-plus>

                                                    <div class="todo-content-container">


                                                        <div class="todo-img">
                                                            <img class="avatar" alt="" src="./img/1.gif">
                                                        </div>
                                                        <div class="todo-content">
                                                            <div class="todo-infos">
                                                                <div class="title-container">
                                                            <span class="title-content">
                                                                <a class="todo-rest" href="">${tasks.number}</a>
                                                            </span>
                                                                </div>
                                                            </div>
                                                            <div class="todo-infos">
                                                        <span class="label todo-project">
                                                                ${tasks.name}
                                                        </span>
                                                            </div>
                                                        </div>
                                                            <div class="todo-due-date">
                                                                <span>${tasks.up}</span>
                                                                &nbsp;&nbsp;
                                                                <span style="color:red;"><i class="twr twr-help"></i></span>
                                                            </div>
                                                    </div>


                                                </tr-todo-item-plus>

                                            </tr-todolist-plus>
                                        </div>
                                    </tr-todos-group>
                                    <div class="init-message">
                                        没有指派给你的任务
                                    </div>
                                </tr-member-todo-box>
                                </c:if>
                                <c:if test="${tasks.isNot == 1}">
                                    <tr-member-todo-box class="member-todo-box" >
                                        <tr-todos-group>
                                            <div class="group-todolists" hidden>
                                                <tr-todolist-plus>
                                                    <tr-todo-item-plus>

                                                        <div class="todo-content-container">


                                                            <div class="todo-img">
                                                                <img class="avatar" alt="" src="./img/1.gif">
                                                            </div>
                                                            <div class="todo-content">
                                                                <div class="todo-infos">
                                                                    <div class="title-container">
                                                            <span class="title-content">
                                                                <a class="todo-rest" href="">${tasks.number}</a>
                                                            </span>
                                                                    </div>
                                                                </div>
                                                                <div class="todo-infos">
                                                        <span class="label todo-project">
                                                                ${tasks.name}
                                                        </span>
                                                                </div>
                                                            </div>
                                                            <div class="todo-due-date">
                                                                <span>${tasks.up}</span>
                                                                &nbsp;&nbsp;
                                                                <span style="color:red;"><i class="twr twr-help"></i></span>
                                                            </div>
                                                        </div>


                                                    </tr-todo-item-plus>

                                                </tr-todolist-plus>
                                            </div>
                                        </tr-todos-group>
                                        <div class="init-message">
                                            没有指派给你的任务
                                        </div>
                                    </tr-member-todo-box>
                                </c:if>

                            </c:forEach>
                        </c:if>
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