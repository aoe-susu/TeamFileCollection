<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Date now = new Date();
    request.setAttribute("now", now);
%>
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
    </style>

    <script>
        function deleteTask(id) {
            if(confirm("您确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/delCollectionTaskServlet?currentPage=${pb.currentPage}&rows=3&id="+id+"&all=${pageContext.request.getParameter("all")}";
            }
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

                <%@include file="_teamInfoLayout.jsp"%>
                <script>
                    $("#page2").attr("class","active");
                </script>

                <div class="member-section member-todos">
                    <tr-member-todo-toolbar>
                        <div class="link-actions more">
                            <c:if test="${filter=='team'}">
                                <a href="${pageContext.request.contextPath}/add.jsp?currentPage=${pb.currentPage}&rows=3&all=${pageContext.request.getParameter("all")}">发布任务</a>
                            </c:if>
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
                                        function uploadFile(taskId){
                                            $("#file").change(function () {
                                                $("#fileForm").attr("action","${pageContext.request.contextPath}/handIn.do?taskId="+taskId)
                                                $("#fileForm").submit();
                                            });
                                            $("#file").click();
                                        }
                                    </script>
                                    <c:forEach items="${pb.list}" var="task" varStatus="s">

                                        <tr-todo-item-plus>
                                            <div class="todo-content-container">
                                                <div class="todo-actions">
                                                    <c:if test="${filter=='team'}">
                                                        <a class="del action" title="删除" href="javascript:deleteTask(${task.id});">
                                                            <i class="twr twr-trash"></i>
                                                        </a>
                                                        <a class="action edit" title="编辑" href="${pageContext.request.contextPath}/findCollectionTaskServlet?currentPage=${pb.currentPage}&rows=4&id=${task.id}&all=${pageContext.request.getParameter("all")}">
                                                            <div class="action"><i class="twr twr-pencil-square-o"></i></div>
                                                        </a>

                                                        <a class="action edit" title="下载文件" target="_blank" href="${pageContext.request.contextPath}/downAll.do?taskId=${task.id}">
                                                            <div class="action"><i class="twr twr-download"></i></div>
                                                        </a>

                                                    </c:if>
                                                    <c:if test="${filter=='member'}">
                                                        <c:if test="${task.deadline>=now}">
                                                            <a class="action edit" title="提交文件" href="javascript:uploadFile(${task.id});">
                                                                <div class="action"><i class="twr twr-upload"></i></div>
                                                            </a>
                                                        </c:if>
                                                        <c:if test="${task.deadline<now}">
                                                            <a class="action edit" style="font-size: 8px;color: red;" href="javascript:;">END</a>
                                                        </c:if>
                                                    </c:if>

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
                                                                <c:if test="${filter=='team'}">
                                                                    <a class="todo-rest" href="${pageContext.request.contextPath}/showAllStu.do?taskId=${task.id}">${task.title}</a>
                                                                </c:if>
                                                                <c:if test="${filter=='member'}">
                                                                    <a class="todo-rest" href="javascript:;">${task.title}</a>
                                                                </c:if>
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
                                    <c:if test="${ft!='time'}">
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
                                    <c:if test="${ft=='time'}">
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
                                        <c:if test="${ft!='time'}">
                                            <c:if test="${pb.currentPage==i}">
                                                <li class="active"><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                            <c:if test="${pb.currentPage!=i}">
                                                <li><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ft=='time'}">
                                            <c:if test="${pb.currentPage==i}">
                                                <li class="active"><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                            <c:if test="${pb.currentPage!=i}">
                                                <li><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=4&teamId=${condition.teamId[0]}&all=${pageContext.request.getParameter("all")}">${i}</a></li>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${ft!='time'}">
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
                                    <c:if test="${ft=='time'}">
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


<%@include file="_footLayout.jsp"%>


</html>
