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
    <meta name="renderer" content="webkit">
    <meta name="baidu-site-verification" content="qLDoHdGnb64RHlkm">
    <meta name="alexaVerifyID" content="SIgQikd9LazsFz9M1vPBaQyC4Gw">
    <meta name="tower-version" content="20.08.21">

    <link rel="dns-prefetch" href="https://avatar-alioss.tower.im/">
    <link rel="dns-prefetch" href="https://atttachments.tower.im/">
    <link rel="shortcut icon" href="https://tower.im/favicon.ico?v=2" type="image/x-icon" id="favicon">
    <link rel="apple-touch-icon" sizes="180x180" href="https://tower.im/apple-touch-icon.png">
    <%--<link rel="manifest" href="https://tower.im/site.webmanifest">--%>
    <link rel="mask-icon" href="https://tower.im/safari-pinned-tab.svg" color="#44acb6">
    <meta name="msapplication-TileColor" content="#44acb6">
    <meta name="theme-color" content="#ffffff">
    <meta name="csrf-param" content="authenticity_token">
    <meta name="csrf-token" content="UzxVyWcKQinMPgnzVsnxQsP9ilBl+GUnNL2POcEYZTGTdfz8jhmboXBNQNkNy8OrmggR9IgFXb9P1T305eJS2g==">
    <!-- 1. 导入CSS的全局样式 -->
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">--%>

    <link rel="stylesheet" media="all" href="css/desktop-2f3f812d.css">
    <link rel="stylesheet" media="all" href="css/tr_organization-dad0b220.css">
    <link rel="stylesheet" media="all" href="css/tr_repository-10ca2615.css">

    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.2.1.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.js"></script>
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
        /*window.onload=function () {
            document.getElementById("delSelected").onclick=function () {
                if(confirm("您确定要删除选中条目吗？")){
                    var flag=false;
                    var cbs = document.getElementsByName("tid");
                    for (var i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked){
                            flag=true;
                            break;
                        }
                    }

                    if(flag){
                        document.getElementById("form").submit();
                    }
                }
            }

            document.getElementById("firstCb").onclick=function () {
                var cbs = document.getElementsByName("tid");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked=this.checked;
                }
            }
        }*/

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
                    <a href="#">团队文件收集系统</a>
                </li>
            </ul>
            <div class="command-bar">
                <div class="twr-search-bar">
                    <a href="" class="link-search"><i class="twr twr-search"></i></a>
                    <input id="txt-search" type="text" class="no-border" placeholder="搜索团队" autocomplete="off">
                </div>
                <%--<div class="twr-search-bar">
                    <a href="#" onclick="document.getElementById('myForm').submit();return false;" class="link-search"><i class="twr twr-search"></i></a>
                    <form action="${pageContext.request.contextPath}/findCollectionTaskByPageServlet" id="myForm">
                        <input id="txt-search" name="teamId" type="text" class="no-border" value="${condition.teamId[0]}"  placeholder="搜索团队" autocomplete="off">
                    </form>

                </div>--%>
                <%--<div style="float:left;">
                    <form class="form-inline" action="${pageContext.request.contextPath}/findCollectionTaskByPageServlet" method="post">
                        <div class="form-group">
                            <label for="exampleInputName2">团队ID</label>
                            <input type="text" name="teamId" value="${condition.teamId[0]}" class="form-control" id="exampleInputName2">
                        </div>
                        <button type="submit" class="btn btn-default">查询</button>
                    </form>
                </div>--%>
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
            <div class="page-inner page-member" id="page-member" >

                <div class="member-info">
                    <img class="avatar" alt="团队名称" src="./img/1.gif">
                    <div class="member-info-content">
                        <div class="member-card">
                            <h1>
                                <span class="name">1704班作业</span>
                                <a title="编辑" href=""><span class="twr twr-pencil-square-o"></span></a>
                            </h1>
                            <span class="member-prop" data-type="online" data-tooltip="桌面版在线"></span>
                            <div class="member-comment">
                                <span></span>
                                <span></span>
                            </div>
                            <p class="member-comment">
                                <span>1704班都是人才，说话又好听，我超爱这里的。</span>
                                <a title="编辑" href=""><span class="twr twr-pencil-square-o"></span></a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="member-nav">
                    <ul>
                        <li class="">
                            <a href="#">概况</a>
                        </li>
                        <li class="active">
                            <a href="#">任务</a>
                        </li>
                        <li class="">
                            <a href="#">成员</a>
                        </li>
                        <li class="">
                            <a href="#">入团申请</a>
                        </li>
                    </ul>
                </div>



                <div class="member-section member-todos">
                    <tr-member-todo-toolbar>
                        <div class="link-actions more">
                            <a href="${pageContext.request.contextPath}/add.jsp?currentPage=${pb.currentPage}&rows=3&all=${pageContext.request.getParameter("all")}">发布任务</a>
                            <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?all=true">查看全部任务</a>
                            <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?all=false">查看未截止任务</a>
                                <%--<button type="submit" class="" id="add" style="border: none">
                                    发布任务
                                </button>

                                <button type="submit" class="" id="button" style="border: none">
                                    查看全部任务
                                </button>
                            &lt;%&ndash;<a href="">查看未截止任务</a>&ndash;%&gt;
                                <button type="button" class="" id="deadline" style="border: none">
                                    查看未截止任务
                                </button>--%>
                        </div>
                    </tr-member-todo-toolbar>

                    <tr-member-todo-box class="member-todo-box ">
                        <tr-todos-group>
                            <div class="group-todolists">
                                <tr-todolist-plus >
                                    <form id="fileForm" action="${pageContext.request.contextPath}/handIn.do" method="post" enctype="multipart/form-data">
                                        <input id="file" type="file" name="handFile" style="display: none;"/>
                                    </form>
                                    <script>
                                        function uploadFile(){
                                            $("#file").change(function () {
                                                $("#fileForm").submit();
                                            });
                                            $("#file").click();
                                        }
                                    </script>
                                    <c:forEach items="${pb.list}" var="task" varStatus="s">

                                        <tr-todo-item-plus>
                                            <div class="todo-content-container">
                                                <div class="todo-actions">
                                                    <a class="del action" title="删除" href="javascript:deleteTask(${task.id});">
                                                        <i class="twr twr-trash"></i>
                                                    </a>
                                                    <a class="action edit" title="编辑" href="${pageContext.request.contextPath}/findCollectionTaskServlet?currentPage=${pb.currentPage}&rows=4&id=${task.id}&all=${pageContext.request.getParameter("all")}">
                                                        <div class="action"><i class="twr twr-pencil-square-o"></i></div>
                                                    </a>
                                                    <a class="action edit" title="提交文件" href="javascript:uploadFile();">

                                                        <div class="action"><i class="twr twr-upload"></i></div>
                                                    </a>
                                                    <a class="action edit" title="下载文件" target="_blank" href="${pageContext.request.contextPath}/downAll.do?taskId=${task.id}">
                                                        <div class="action"><i class="twr twr-download"></i></div>
                                                    </a>
                                                </div>
                                                <div class="todo-content">
                                                    <div class="todo-title-container">
                                                        <div class="todo-sortable-handle">
                                                            <i class="twr twr-drag"></i>
                                                        </div>
                                                        <div class="title-container">
                                                            &nbsp;<span>${s.count}</span>.&nbsp;
                                                            <span class="runner twr"></span>
                                                            <span class="title-content">
                                                                <a class="todo-rest" href="${pageContext.request.contextPath}/showAllStu.do">${task.title}</a>
                                                        </span>
                                                        </div>
                                                    </div>
                                                    <div class="todo-infos">
                                                        <span class="todo-rest">
                                                                ${task.content}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="todo-due-date">
                                                    <span>截止至：</span><span><fmt:formatDate type="time" value="${task.deadline}" pattern="yyyy-MM-dd" /></span>
                                                </div>
                                            </div>
                                        </tr-todo-item-plus>
                                    </c:forEach>


                                </tr-todolist-plus>
                            </div>
                        </tr-todos-group>

                        <div class="pagediv">
                            <br/><br/>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:if test="${filter!='time'}">
                                        <c:if test="${pb.currentPage==1}">
                                            <li class="disabled" style="pointer-events:none">
                                                <a class="btn btn-prev-month" href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage-1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Previous">
                                                    <span class="twr twr-angle-left" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pb.currentPage!=1}">
                                            <li>
                                                <a class="btn btn-prev-month" href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage-1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Previous">
                                                    <span class="twr twr-angle-left" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${filter=='time'}">
                                        <c:if test="${pb.currentPage==1}">
                                            <li class="disabled" style="pointer-events:none">
                                                <a class="btn btn-prev-month" href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage-1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Previous">
                                                    <span class="twr twr-angle-left" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pb.currentPage!=1}">
                                            <li>
                                                <a class="btn btn-prev-month" href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage-1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Previous">
                                                    <span class="twr twr-angle-left" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:if>

                                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                                        <c:if test="${filter!='time'}">
                                            <c:if test="${pb.currentPage==i}">
                                                <li class="active"><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                            <c:if test="${pb.currentPage!=i}">
                                                <li><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${filter=='time'}">
                                            <c:if test="${pb.currentPage==i}">
                                                <li class="active"><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                            <c:if test="${pb.currentPage!=i}">
                                                <li><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${filter!='time'}">
                                        <c:if test="${pb.currentPage==pb.totalPage}">
                                            <li class="disabled" style="pointer-events:none">
                                                <a class="btn btn-next-month" href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage+1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Next">
                                                    <span class="twr twr-angle-right" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pb.currentPage!=pb.totalPage}">
                                            <li>
                                                <a class="btn btn-next-month"  href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage+1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Next">
                                                    <span class="twr twr-angle-right" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${filter=='time'}">
                                        <c:if test="${pb.currentPage==pb.totalPage}">
                                            <li class="disabled" style="pointer-events:none">
                                                <a class="btn btn-next-month"  href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage+1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Next">
                                                    <span class="twr twr-angle-right" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pb.currentPage!=pb.totalPage}">
                                            <li>
                                                <a class="btn btn-next-month" href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage+1}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}" aria-label="Next">
                                                    <span class="twr twr-angle-right" aria-hidden="true"></span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:if>
                                    <span style="font-size: 20px;margin-left: 5px">
                                        共${pb.totalCount}条记录，共${pb.totalPage}页
                                    </span>
                                </ul>
                            </nav>
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
</html>
