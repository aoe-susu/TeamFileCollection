function checknum() {
    let num = document.getElementById("num").value;
    let checkN=document.getElementById("checkNumResult");
    if (num=="") {
       checkN.innerHTML="编号不能为空！"
        checkN.style.color="red";
        return false;
    } else {
        checknumExists();
    }
}

function checkname() {
    let name = document.getElementById("name").value;
    let checkn=document.getElementById("checkNumResult");
    if (name == "") {
       checkn.innerHTML="姓名不能为空！"
        checkn.style.color="red";
       return false;
    } else {
        checkn.innerHTML="";
    }
}
function checknumExists(num) {
    let prompt = document.getElementById("mess");
    $.post("existMemberNumber.do",{num:num},function (data) {
        if (data === 'true'){
            prompt.innerHTML = "<font style='color: #ff0000'>该账号太受欢迎，请重新输入</font>";
        } else {
            prompt.innerHTML = "<font style='color: greenyellow'></font>";
        }
    });
}





