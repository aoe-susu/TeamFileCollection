$(document).ready(function() {
    document.getElementById("loginBntId").onclick = function() {
        $('#loginModalId').modal('show');
    }
    document.getElementById("loginModalYesId").onclick = function() {
        $('#loginModalId').modal('hide');
        alert("登录功能未实现！");
    }
});