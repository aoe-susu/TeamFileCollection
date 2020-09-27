//验证账号是否已经被注册
function checkAccountExists(account) {
    var prompt = document.getElementById("mess");
    $.post("validateAccount.do", {account: account}, function (data) {
        if (data == 'true') {
            prompt.innerHTML = "<font style='color: red'>该账号太受欢迎，请重新输入</font>";
        } else {
            prompt.innerHTML = "<font style='color: greenyellow'></font>";
        }
    });
}

//修改密码时验证输入的旧密码是否正确
function checkPasswordExist(oldPassword) {
    var prompt = document.getElementById("mess");
    $.post("validatePassword.action", {oldPassword: oldPassword}, function (data) {
        if (data == 'true') {
            prompt.innerHTML = "<font style='color: greenyellow'></font>";
            document.getElementById("submit").disabled = false;

        } else {
            prompt.innerHTML = "<font style='color: red'>密码输入有误！</font>";
           document.getElementById("submit").disabled = true;
        }
    });
}

//验证两次输入的密码是否一致
function checkpassword() {
    // var oldPassword = $("#oldPassword").val();
    var password = $("#pwd").val()
    var repassword = $("#repwd").val();
    if (password == repassword) {
        document.getElementById("pa").innerHTML = "<font color='greenyellow'>两次输入的新密码一致</font>";
    } else if (password != repassword) {
        document.getElementById("pa").innerHTML = "<font color='red'>两次输入的新密码不一致</font>";
    }


}

//切换验证码
function refreshCode() {
    //获取验证码图片对象
    var vcode = document.getElementById("vcode");
    //设置src属性，加时间戳
    vcode.src = "${pageContext.request.contextPath}/checkCode.action?time" + new Date().getTime();
}


/*
//显示隐藏登录表单
function login() {
    var id = document.getElementById("form");
    id.style.display='';
}
*/

//登录输入框账号非空
function checkAccountNull() {
    var account = document.getElementById("account-1").value;
    if (account == "") {
        document.getElementById("account-2").innerHTML = "<font color='red'>账号不能为空</font>";
    } else {
        document.getElementById("account-2").innerHTML = "<font color='red'></font>";
    }
}

//登录输入框密码非空
function checkPwdNull() {
    var password = document.getElementById("pwd-1").value;
    if (password == "") {
        document.getElementById("pwd-2").innerHTML = "<font color='red'>密码不能为空</font>";
    } else {
        document.getElementById("pwd-2").innerHTML = "<font color='red'></font>";
    }
}

//登录输入框验证码非空
function checkVarifycodeNull() {
    var varifcode = document.getElementById("varifcode-1").value;
    if (varifcode == "") {
        document.getElementById("varifcode-2").innerHTML = "<font color='red'>验证码不能为空</font>";
    } else {
        document.getElementById("varifcode-2").innerHTML = "<font color='red'></font>";
    }
}

//修改team名称
function modifyTeamName() {
    $("#teamName_modify_input").val($("#teamName").html());
    //弹出模态框
    $("#teamNameModifyModal").modal({
        backdrop: "static"
    });
}

//点击修改名称按钮弹出模态框。
function updateName() {
    $.ajax({
        url: "${pageContext.request.contextPath}/modifyTeamName.action",
        type: "POST",
        async: false,
        data: $("#teamNameModifyModal form").serialize(),
        success: function (result) {
            var data = result.split(" ");
            if (data[0] == 'true') {
                //员工保存成功；
                //1、关闭模态框
                $("#teamNameModifyModal").modal('hide');
                //2、显示刚才保存的数据
                $("#teamName").html(data[2]);
                $("#name").html(data[2]);
            }
        }
    });
}

//修改team简介
function modifyTeamIntroduction() {
    $("#introduction_modify_input").val($("#teamIntroduction").html());
    //弹出模态框
    $("#teamIntroductionModifyModal").modal({
        backdrop: "static"
    });
}

//点击修改简介按钮弹出模态框。
function updateIntro() {
    $.ajax({
        url: "${pageContext.request.contextPath}/modifyTeamIntroduction.action",
        type: "POST",
        async: false,
        data: $("#teamIntroductionModifyModal form").serialize(),
        success: function (result) {
            var data = result.split(" ");
            if (data[0] == 'true') {
                //员工保存成功；
                //1、关闭模态框
                $("#teamIntroductionModifyModal").modal('hide');
                //2、显示刚才保存的数据
                $("#teamIntroduction").html(data[2]);
                $("#introduction").html(data[2]);
            }
        }
    });
}

//修改team图标
function modifyTeamIconUrl() {
    $("#teamHeadImg").attr("src",$("#myHeadUrl").attr("src"));
    $("#modefyHeadImgModal").show();
    //document.location.preur
}


function doupdate(form) {
    /*$.ajax({
        url: "modifyTeamIconUrl.do",
        type: "POST",
        data: $(form).serialize(),
        success: function (data) {
            //  var json=eval(data);
            if (data == 'false') {
                alert('添加失败！');
            } else {
                $('#fill').hide();
                $('#myform').hide();
                //$(form).find(":reset").trigger("click");
                //load();//重新加载
                $(".mying").attr("src", data);
            }
        }
    });*/
    return true;
}


/*//修改team密码
function modifyTeamIntroduction() {
    var teamId = $("#teamId").html();
    $.get("getTeamById.do", {id: teamId}, function (data) {
        $("#team_modifyId_input").val(data.id);

    }, "json");
    //弹出模态框
    $("#teamPasswordModifyModal").modal({
        backdrop: "static"
    });
}
//点击修改简介按钮弹出模态框。
function updatePassword() {
    $.ajax({
        url: "${pageContext.request.contextPath}/modifyTeamPassword.do",
        type: "POST",
        async: false,
        data: $("#teamPasswordModifyModal form").serialize(),
        success: function (result) {

            if (data[0]=='true') {
                //员工保存成功；
                //1、关闭模态框
                $("#teamIntroductionModifyModal").modal('hide');
                //2、显示刚才保存的数据
                $("#teamIntroduction").html(data[2]);
                $("#introduction").html(data[2]);
            }
        }
    });
}*/

