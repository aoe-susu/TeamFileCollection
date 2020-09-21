<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>添加任务</h3></center>
    <form action="${pageContext.request.contextPath}/addCollectionTaskServlet" method="post">
        <div class="form-group">
            <label for="title">任务标题：</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入任务标题">
        </div>

        <div class="form-group">
            <label for="content">任务说明：</label>
            <input type="text" class="form-control" id="content" name="content" placeholder="请输入任务说明">
        </div>

        <div class="form-group">
            <label for="teamId">团队ID：</label>
            <input type="text" class="form-control" id="teamId" name="teamId" placeholder="请输入团队ID">
        </div>

        <div class="form-group">
            <label for="deadline">任务截止时间：</label>
            <%--<input type="date" class="form-control" id="deadline" name="deadline" value='<fmt:formatDate value="${officeInspectionOrder.REPORT_DATE}" pattern="yyyy-MM-dd" />' />--%>
            <input type="date" class="form-control" id="deadline" name="deadline" value='<fmt:formatDate value="${Date}" pattern="yyyy-MM-dd" />' />
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置"  />
            <input class="btn btn-default" type="button" value="返回" onclick="javascript:history.back(-1);"/>
        </div>
    </form>
</div>
</body>

