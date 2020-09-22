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
    <meta name="renderer" content="webkit">
    <meta name="baidu-site-verification" content="qLDoHdGnb64RHlkm">
    <meta name="alexaVerifyID" content="SIgQikd9LazsFz9M1vPBaQyC4Gw">
    <meta name="tower-version" content="20.08.21">
    <link rel="dns-prefetch" href="https://avatar-alioss.tower.im/">
    <link rel="dns-prefetch" href="https://atttachments.tower.im/">
    <link rel="shortcut icon" href="https://tower.im/favicon.ico?v=2" type="image/x-icon" id="favicon">
    <link rel="apple-touch-icon" sizes="180x180" href="https://tower.im/apple-touch-icon.png">
    <link rel="manifest" href="https://tower.im/site.webmanifest">
    <link rel="mask-icon" href="https://tower.im/safari-pinned-tab.svg" color="#44acb6">
    <meta name="msapplication-TileColor" content="#44acb6">
    <meta name="theme-color" content="#ffffff">

    <link rel="stylesheet" media="all" href="./css/desktop-2f3f812d.css">
    <style>

        body {
            background: #F4F7ED;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>

    <link rel="stylesheet" media="all" href="./css/tr_organization-dad0b220.css">
    <link rel="stylesheet" media="all" href="./css/tr_repository-10ca2615.css">

    <meta name="csrf-param" content="authenticity_token">
    <meta name="csrf-token" content="UzxVyWcKQinMPgnzVsnxQsP9ilBl+GUnNL2POcEYZTGTdfz8jhmboXBNQNkNy8OrmggR9IgFXb9P1T305eJS2g==">


</head>
<body class="">

    <div class="wrapper">

        <div class="header  main-header">
            <div class="header-container">
                <div class="header-left">

                    <tr-header-team-menu tao-id="1">
                        <tr-menu class="tr-menu mdc-menu-surface--anchor menu-tao-id-2">
                            <a class="link-team-name" href="">
                                <span class="team-name">TeamFileCollection</span>
                            </a>
                        </tr-menu>
                    </tr-header-team-menu>
                </div>
                <ul class="nav">
                    <li class="">
                        <a href="">团队文件收集系统</a>
                    </li>
                </ul>
                <div class="command-bar">
                    <div class="twr-search-bar">
                        <a href="" class="link-search"><i class="twr twr-search"></i></a>
                        <input id="txt-search" type="text" class="no-border" placeholder="搜索团队" autocomplete="off">
                    </div>
                    <div class="account-info">
                        <div class="member-settings">
                            <a class="link-member-menu" href="javascript:;">
                                <img class="avatar" alt="" src="./img/1.gif">
                                <span class="twr twr-caret-down"></span>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <div class="container workspace simple-stack simple-stack-transition">
            <div class="page page-root simple-pjax">
                <div class="page-inner page-member" id="page-member">

                    <div class="member-info">
                        <img class="avatar" alt="团队名称" src="./img/1.gif">
                        <div class="member-info-content">
                            <div class="member-card">
                                <h1>
                                    <span class="name">团队名称</span>
                                    <a title="编辑" href=""><span class="twr twr-pencil-square-o"></span></a>
                                </h1>
                                <span class="member-prop" data-type="online" data-tooltip="桌面版在线"></span>
                                <div class="member-comment">
                                    <span></span>
                                    <span></span>
                                </div>
                                <p class="member-comment">
                                    <span>这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介这里是团队简介</span>
                                    <a title="编辑" href=""><span class="twr twr-pencil-square-o"></span></a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="member-nav">
                        <ul>
                            <li class="">
                                <a href="../teamIntroduction.html">概况</a>
                            </li>
                            <li class="">
                                <a href="../teamTask.html">任务</a>
                            </li>
                            <li class="">
                                <a href="../teamMember.html">成员</a>
                            </li>
                            <li class="">
                                <a href="../teamApplication.html">入团申请</a>
                            </li>
                            <li class="active">
                                <a href="javascript:;">任务提交详情</a>
                            </li>
                        </ul>
                    </div>



                    <div class="member-section member-todos">
                        <tr-member-todo-toolbar>
                            <span>第一次实验报告</span>
                            <span>（${upCount}/${totalCount}）</span>
                            <div class="link-actions more">
                                <a href="${pageContext.request.contextPath}/downAll.do">压缩全部任务文件并下载</a>
                                <a href="">查看全部</a>
                                <a href="${pageContext.request.contextPath}/showNotHand.do">查看未提交</a>
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
                                                        <a class="action edit" title="下载文件" href="${pageContext.request.contextPath}/down.do?id=${tasks.id}">
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
</html>