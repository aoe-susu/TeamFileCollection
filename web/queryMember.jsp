<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/9/18
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript"></script>
    <script src="js/jquery-3.2.1.js"></script>
    <script>
        function del1(id) {
            if(confirm("确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/delete1.do?id="+id;
            }

        }

        function modifyIconUrl() {
            $("#fill").show();
            $("#myForm").show();
            var id = $("#id").html();
            var args = {"id": id};
            $.post("getTeamMemberById.do", args, function (data) {
                $("#headImg").val(data.img);
            }, "json");

        }

        function doUpdate(form) {
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

    </script>

</head>
<body>
<form action="${pageContext.request.contextPath}/modifyIconUrl.do" id="form" method="post">
    <div class="result-content">
        <table class="result-tab" width="60%">
            <tr>
                <td>成员ID</td>
                <td>成员编号</td>
                <td>成员名</td>
                <td>头像</td>
                <td>团队ID</td>
                <td>操作</td>
            </tr>
            <c:forEach var="p" items="${teamMember}">
                <tr>
                    <td id="id">${p.id }</td>
                    <td>${p.number }</td>
                    <td>${p.name }</td>
                    <td>
                        <img src="${p.iconUrl}" id="teamMemberHeadImg" alt="团队头像" width="40px" height="40px">
                    </td>
                    <td>${p.teamId }</td>
                    <td><input   type="button" value="删除" onclick="del1(${p.id})" ></td>
                    <td><%--<input   type="button" value="修改头像" onclick="modifyIconUrl() " >--%>
                        <a href="javascript:void(0);" onclick="modifyIconUrl()">修改头像</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

</form>
</body>

<div id="myForm"
     style="width: 100%; height: 100%; position: absolute; top: 0px; left: 0px; z-index: 101; display: none">
    <form action="${pageContext.request.contextPath}/modifyIconUrl.do" method="post"
          onsubmit="return doUpdate(this)"
          enctype="multipart/form-data">
        <table width="300px" border="0" style=" margin: 10% auto; background-color: #fff03f">
            <tr>
                <td colspan="2">
                    <img src="${p.iconUrl}" id="headImg" alt="团队头像" width="80px" height="80px">
                    <input type="file" name="fileToUpload" id="fileToUpload" accept="image/*"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <input type="reset" onclick="$('#fill').hide();$('#myForm').hide();" value="取消"/>
                </td>
                <td align="left"><input type="submit"  value="确定"/></td>
            </tr>
        </table>
    </form>
</div>

</html>
