<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改任务</h3>
        <form action="${pageContext.request.contextPath}/updateCollectionTaskServlet?currentPage=${pageContext.request.getParameter("currentPage")}&rows=3&all=${pageContext.request.getParameter("all")}" method="post">
            <!--隐藏域 提交id -->
            <input type="hidden" name="id" value="${task.id}">
          <div class="form-group">
            <label for="title">任务标题：</label>
            <input type="text" class="form-control" id="title" name="title" value="${task.title}" placeholder="请输入标题" />
          </div>


          <div class="form-group">
            <label for="content">任务说明：</label>
            <input type="text" class="form-control" id="content"  name="content" value="${task.content}"  />
          </div>


          <div class="form-group" hidden>
            <label for="teamId">团队ID：</label>
            <input type="text" class="form-control" name="teamId" id="teamId" readonly="readonly"  value="${task.teamId}" />
          </div>

          <div class="form-group">
            <label for="deadline">截止时间：</label>
            <%--<input type="text" class="form-control" name="deadline" id="deadline" value="${task.deadline}" />--%>
            <input type="date" class="form-control" id="deadline" name="deadline" value='<fmt:formatDate value="${task.deadline}" pattern="yyyy-MM-dd" />' />
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置"  />
                <input class="btn btn-default" type="button" value="返回" onclick="javascript:history.back(-1);"/>
             </div>
        </form>
        </div>
    </body>
</html>