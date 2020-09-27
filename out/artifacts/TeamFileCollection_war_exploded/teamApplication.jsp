<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- saved from url=(0076)https://tower.im/members/e43457750de8deb93cb2b9a276c02112/todos/uncompleted/ -->
<html lang="zh-cmn-Hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>团队申请 - TFC</title>
    <%@include file="_headLayout.jsp"%>
    <script>
        function add(applicationId) {
            if(confirm("是否同意入团申请？")){
                <%--"${pageContext.request.contextPath}/add.do?id="+id+"&number="+number+"&name="+name+"&teamId="+teamId;--%>
                //console.log("${pageContext.request.contextPath}/addMember.fuc?id="+id+"&number="+number+"&name="+name+"&teamId="+teamId);
                location.href= "${pageContext.request.contextPath}/addMember.fuc?applicationId="+applicationId;
            }
        }
        function del1(applicationId) {
            if(confirm("是否拒绝申请？")){
                location.href="${pageContext.request.contextPath}/deleteApplication.fuc?applicationId="+applicationId;
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
                        $("#page4").attr("class","active");
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
                                                        <a class="del action" title="拒绝申请" href="javascript:del1(${item.id});">
                                                            <i class="twr twr-trash"></i>
                                                        </a>
                                                        <a class="del action" title="同意申请" href="javascript:add(${item.id});">
                                                            <i class="twr twr-read twr-checkitem-checked-outline icon"></i>
                                                        </a>
                                                    </div>
                                                    <div class="todo-img">
                                                        <img class="avatar" alt="" src="./img/1.gif">
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