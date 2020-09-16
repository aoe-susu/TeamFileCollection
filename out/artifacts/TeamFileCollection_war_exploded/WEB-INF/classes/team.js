//获取xmlhttprequest对象
function getajaxHttp() {
    var xmlHttp;
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        // Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject(Microsoft.XMLHTTP);
            } catch (e) {
                alert("您的浏览器不支持AJAX！");
                return false;
            }
        }
    }
    return xmlHttp;
}

//验证账号是否已经被注册
function checkAccountExists(account) {
    var prompt = document.getElementById("mess");
    //发送ajax
    //1 创建xmrHttpRequest
    var xhr = getajaxHttp();
    //2 建立连接
    xhr.open("post", "validate.do?account=" + account, true);
    //3 发送连接
    xhr.send(null);
    // 4设置回调函数
    xhr.onreadystatechange = function () {
        //判断readystate and status
        if (xhr.readyState == 4 && xhr.status == 200) {
            //需要根据服务器的回复做相应的处理
            if (xhr.responseText == 'true') {
                prompt.innerHTML = "<font style='color: red'>该账号太受欢迎，请重新输入</font>";
            } else {
                prompt.innerHTML = "<font style='color: greenyellow'>该账号可用</font>";
        }
        }
    }
}

//验证两次输入的密码是否一致
function checkpassword() {
    var password = document.getElementById("pwd").value;
    var repassword = document.getElementById("repwd").value;

    if (password == repassword) {
        document.getElementById("pa").innerHTML = "<font color='greenyellow'>两次密码输入一致</font>";
        document.getElementById("submit").disabled = false;

    } else {
        document.getElementById("pa").innerHTML = "<font color='red'>两次输入密码不一致!</font>";
        document.getElementById("submit").disabled = true;
    }

}

//切换验证码
function refreshCode() {
    //获取验证码图片对象
    var vcode = document.getElementById("vcode");
    //设置src属性，加时间戳
    vcode.src = "${pageContext.request.contextPath}/checkCode.do?time" + new Date().getTime();
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
    if(account==""){
        document.getElementById("account-2").innerHTML = "<font color='red'>账号不能为空</font>";
    }else {
        document.getElementById("account-2").innerHTML = "<font color='red'></font>";
    }
}

//登录输入框密码非空
function checkPwdNull() {
    var password = document.getElementById("pwd-1").value;
    if(password==""){
        document.getElementById("pwd-2").innerHTML = "<font color='red'>密码不能为空</font>";
    }else {
        document.getElementById("pwd-2").innerHTML = "<font color='red'></font>";
    }
}

//登录输入框验证码非空
function checkVarifycodeNull() {
    var varifcode = document.getElementById("varifcode-1").value;
    if(varifcode==""){
        document.getElementById("varifcode-2").innerHTML = "<font color='red'>验证码不能为空</font>";
    }else {
        document.getElementById("varifcode-2").innerHTML = "<font color='red'></font>";
    }
}
