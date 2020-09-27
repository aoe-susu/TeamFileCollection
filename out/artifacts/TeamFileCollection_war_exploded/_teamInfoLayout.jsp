<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="member-info">
    <img id="headImg" class="avatar" alt="团队名称" src="${team.iconUrl}">
    <div class="member-info-content">
        <div class="member-card">
            <h1>
                <span id="teamName" class="name">${team.name}</span>
                <c:if test="${filter=='team'}">
                    <a title="编辑" href="javascript:modifyTeamName();"><span class="twr twr-pencil-square-o"></span></a>

                </c:if>
            </h1>
            <span class="member-prop" data-type="online" data-tooltip="桌面版在线"></span>
            <div class="member-comment">
                <span></span>
                <span></span>
            </div>
            <p class="member-comment">
                <span id="teamIntroduction">${team.introduction}</span>
                <c:if test="${filter=='team'}">
                    <a title="编辑" href="javascript:modifyTeamIntroduction();"><span class="twr twr-pencil-square-o"></span></a>

                </c:if>

            </p>
        </div>
    </div>
</div>

<div class="member-nav">
    <ul>
        <li id="page1" class="">
            <a href="${pageContext.request.contextPath}/getIntroduction.action?teamId=${team.id}">概况</a>
        </li>
        <c:if test="${filter!='visitor'}">
            <li id="page2" class="">
                <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?all=true">任务</a>
            </li>
        </c:if>
        <c:if test="${filter=='team'}">
            <li id="page3" class="">
                <a href="${pageContext.request.contextPath}/getAllMember.fuc">成员</a>
            </li>
            <li id="page4" class="">
                <a href="${pageContext.request.contextPath}/getApplyMember.fuc">入团申请</a>
            </li>
        </c:if>

    </ul>
</div>