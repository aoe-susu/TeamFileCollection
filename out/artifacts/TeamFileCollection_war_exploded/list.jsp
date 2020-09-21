<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>团队任务管理</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.2.1.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>

        function deleteTask(id) {
            if(confirm("您确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/delCollectionTaskServlet?id="+id;
            }
        }

        window.onload=function () {
            document.getElementById("delSelected").onclick=function () {
                if(confirm("您确定要删除选中条目吗？")){
                    var flag=false;
                    var cbs = document.getElementsByName("tid");
                    for (var i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked){
                            flag=true;
                            break;
                        }
                    }

                    if(flag){
                        document.getElementById("form").submit();
                    }
                }
            }

            document.getElementById("firstCb").onclick=function () {
                var cbs = document.getElementsByName("tid");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked=this.checked;
                }
            }
        }

        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').focus();
        })


    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">任务发布列表</h3>

    <div style="float:left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findCollectionTaskByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">团队ID</label>
                <input type="text" name="teamId" value="${condition.teamId[0]}" class="form-control" id="exampleInputName2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right;margin: 5px">
        <%--<a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">发布任务</a>--%>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                添加任务
            </button>

            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">添加任务</h4>
                        </div>
                        <div class="modal-body">
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

                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">提交</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>任务标题</th>
            <th>任务说明</th>
            <th>团队ID</th>
            <th>任务截止时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="task" varStatus="s">
            <tr>
                <th><input type="checkbox" name="tid" id="tid" value="${task.id}"></th>
                <td>${s.count}</td>
                <td>${task.title}</td>
                <td>${task.content}</td>
                <td>${task.teamId}</td>
                <td><fmt:formatDate type="time" value="${task.deadline}" pattern="yyyy-MM-dd" /></td>
                <td>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findCollectionTaskServlet?id=${task.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:deleteTask(${task.id});">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">

                <c:if test="${pb.currentPage==1}">
                <li class="disabled" style="pointer-events:none">
                    <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage-1}&rows=5&teamId=${condition.teamId[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                </c:if>
                <c:if test="${pb.currentPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage-1}&rows=5&teamId=${condition.teamId[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                </c:if>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">

                    <c:if test="${pb.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=5&teamId=${condition.teamId[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage!=i}">
                        <li><a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${i}&rows=5&teamId=${condition.teamId[0]}">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${pb.currentPage==pb.totalPage}">
                    <li class="disabled" style="pointer-events:none">
                        <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage+1}&rows=5&teamId=${condition.teamId[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pb.currentPage!=pb.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findCollectionTaskByPageServlet?currentPage=${pb.currentPage+1}&rows=5&teamId=${condition.teamId[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <span style="font-size: 25px;margin-left: 5px">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
