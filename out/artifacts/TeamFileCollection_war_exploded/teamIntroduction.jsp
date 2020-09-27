<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.four.entity.Team" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- saved from url=(0076)https://tower.im/members/e43457750de8deb93cb2b9a276c02112/todos/uncompleted/ -->
<html lang="zh-cmn-Hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>团队概况 - TFC</title>
    <%@include file="_headLayout.jsp"%>
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
            $.post("existMemberNumber.fuc", {num: num}, function (data) {
                if (data == 'true') {
                    prompt.innerHTML = "<font style='color: red'>该编号已存在！</font>";
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
<body class="">

<div class="wrapper">

    <%@include file="_myInfoLayout.jsp"%>

    <div class="container workspace simple-stack simple-stack-transition">
        <div class="page page-root simple-pjax">
            <div class="page-inner page-member" id="page-member">

                <%@include file="_teamInfoLayout.jsp"%>
                <script>
                    $("#page1").attr("class","active");
                </script>

                <div class="member-section">

                    <tr-todo-item-plus class="intro">
                        <div class="intro-part">
                            <div>
                                <span class="twr twr-team">成员</span>
                            </div>
                            <div class="intro-num">
                                <span>4</span>
                            </div>
                        </div>

                        <div class="intro-part">
                            <div>
                                <span class="twr twr-todo-desc">任务</span>
                            </div>
                            <div class="intro-num">
                                <span>9</span>
                            </div>
                        </div>
                    </tr-todo-item-plus>
                </div>
                <c:if test="${filter=='visitor'}">
                    <dic class="member-section">
                        <a class="tr-button mdc-button mdc-button--outlined mdc-button--dense" href="javascript:$('#enterTeamModal').show();">
                            <span class="mdc-button__label">进入团队</span>
                        </a>
                        <a class="tr-button mdc-button mdc-button--outlined mdc-button--dense" href="javascript:$('#applyModal').show();">
                            <span class="mdc-button__label">申请加入 </span>
                        </a>
                    </dic>
                </c:if>


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
