<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a href="${pageContext.request.contextPath}/creatTeam.jsp">创建团队</a>
            </li>
        </ul>
        <div class="command-bar">
<%--            <div class="twr-search-bar">--%>
<%--                <a href="" class="link-search"><i class="twr twr-search"></i></a>--%>
<%--                <input id="txt-search" type="text" class="no-border" placeholder="搜索团队" autocomplete="off">--%>
<%--            </div>--%>
            <c:if test="${filter=='team'}">
                <div class="account-info">
                    <div class="member-settings">
                        <a class="link-member-menu" href="javascript:;">
                            <img id="myHeadUrl" class="avatar" alt="" src="${team.iconUrl}">
                            <span class="twr twr-caret-down"></span>

                        </a>
                    </div>
                    <div class="downOut">
                        <a href="javascript:$('#modefyPasswordModal').show();">修改密码</a>
                        <a href="javascript:modifyTeamIconUrl();">修改头像</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${filter=='member'}">
                <div class="account-info">
                    <div class="member-settings">
                        <a class="link-member-menu" href="javascript:;">
                            <img id="myHeadUrl" class="avatar" alt="" src="${member.iconUrl}">
                            <span class="twr twr-caret-down"></span>

                        </a>
                    </div>
                    <div class="downOut">
                        <a href="javascript:modifyTeamIconUrl();">修改头像</a>
                    </div>
                </div>
            </c:if>

        </div>
    </div>
</div>