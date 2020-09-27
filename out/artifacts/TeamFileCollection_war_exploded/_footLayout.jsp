<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--弹出框--%>
<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="msg" style="text-align: center;">进入团队</h4>
            </div>
        </div>

    </div>
</div>
</div>

<%--进入团队--%>
<div class="modal fade" id="enterTeamModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">进入团队</h4>
            </div>
            <hr/>
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login.action">
                <div class="modal-body">
                    <input type="hidden" name="teamId"  value="${team.id}"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名：</label><br/>
                        <input type="text" name="account" id="account-1" onblur="checkAccountNull()"/>
                        <div id="account-2" style="display: inline"></div>
                        <br/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码：</label><br/>
                        <input type="password" name="password" id="pwd-1" onblur="checkPwdNull()"/>
                        <div id="pwd-2" style="display: inline"></div>
                        <br/>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">验证码：</label><br/>
                        <input type="text" name="varifycode" placeholder="请输入验证码" id="varifcode-1" onblur="checkVarifycodeNull()"/>
                        <div id="varifcode-2" style="display: inline"></div><br/>
                        <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCode.action" title="看不清点击刷新" id="vcode"/></a>
                        <br/>
                    </div>

                </div>
                <hr/>
                <div class="modal-footer">

                    <button type="submit" class="btn btn-primary" data-dismiss="modal">进入</button>
                    <button type="button" class="btn btn-default" onclick="$('#enterTeamModal').hide();" data-dismiss="modal">关闭</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%--申请加入团队--%>
<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">申请加入团队</h4>
            </div>
            <hr/>
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/addApplication.fuc?teamId=${team.id}">
                <div class="modal-body">
                    <input type="hidden" name="teamId"  value="${team.id}"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">成员编号：</label><br/>
                        <input type="text" name="num" id="num" onblur="checknumExists(this.value)"/>
                        <div id="checknumResult" style="display: inline"></div>
                        <br/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称：</label><br/>
                        <input type="text" name="name" id="name" onblur="checkname(this.value)"/>
                        <div id="checknameResult" style="display: inline"></div>
                        <br/>

                    </div>
                </div>
                <hr/>
                <div class="modal-footer">

                    <button type="submit" class="btn btn-primary" data-dismiss="modal">申请</button>
                    <button type="button" class="btn btn-default" onclick="$('#applyModal').hide();" data-dismiss="modal">关闭</button>
                </div>
            </form>

        </div>
    </div>
</div>


<%--修改团队密码--%>
<div class="modal fade" id="modefyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">进入团队</h4>
            </div>
            <hr/>
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/modifyTeamPassword.action">
                <div class="modal-body">
                    <div class="form-group">
                        原密码：<br/><input type="password" name="oldPassword" id="oldPassword" onblur="checkPasswordExist(this.value)"/>
                        <div id="mess" style="display: inline"></div><br/>
                    </div>
                    <div class="form-group">
                        新密码：<br/><input type="password" name="newPassword" id="pwd"  />
                        <div id="pa1" style="display: inline"></div><br/>
                    </div>
                    <div class="form-group">
                        再次输入：<br/><input type="password" name="rePassword" id="repwd" onkeyup="checkpassword()"/>
                        <div id="pa" style="display: inline"></div><br/>
                    </div>

                </div>
                <hr/>
                <div class="modal-footer">

                    <button type="submit" class="btn btn-primary" data-dismiss="modal">修改</button>
                    <button type="button" class="btn btn-default" onclick="$('#modefyPasswordModal').hide();" data-dismiss="modal">关闭</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%--修改头像--%>
<div class="modal fade" id="modefyHeadImgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">进入团队</h4>
            </div>
            <hr/>
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/modifyTeamIconUrl.action"
                  onsubmit="return doupdate(this)" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <img src="${team.iconUrl}" id="teamHeadImg" alt="团队头像" width="80px" height="80px">
                        <input type="file" name="fileToUpload" id="fileToUpload" accept="image/*"/>
                    </div>
                </div>
                <hr/>
                <div class="modal-footer">
                    <button type="submit" id="submit" class="btn btn-primary" data-dismiss="modal">修改</button>
                    <button type="button" class="btn btn-default" onclick="$('#modefyHeadImgModal').hide();" data-dismiss="modal">关闭</button>
                </div>
            </form>

        </div>
    </div>
</div>


<%--修改团队名称--%>
<div class="modal fade" id="teamNameModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="name_modifyModalLabel">团队名称修改</h4>
            </div>
            <hr/>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称：</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" id="teamName_modify_input"
                                   value="${team.name}" placeholder="teamName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <hr/>
            <div class="modal-footer">

                <button type="button" class="btn btn-primary" id="teamName_modify_btn" onclick="updateName()">保存 </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--修改团队简介--%>
<div class="modal fade" id="teamIntroductionModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="intro_modifyModalLabel">团队简介修改</h4>
            </div>
            <hr/>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">简介：</label>
                        <div class="col-sm-10">
                            <textarea type="text" name="introduction" class="form-control" id="introduction_modify_input"
                                      placeholder="introduction">${team.introduction}</textarea>
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <hr/>
            <div class="modal-footer">

                <button type="button" class="btn btn-primary" id="teamIntro_modify_btn" onclick="updateIntro()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>