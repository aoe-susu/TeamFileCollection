package com.four.servlet;

import com.four.entity.CollectionTask;
import com.four.entity.PageBean;
import com.four.service.CollectionTaskService;
import com.four.serviceimpl.CollectionTaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/findCollectionTaskByPageServlet")
public class FindCollectionTaskByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数
        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="3";
        }

        //获取条件查询的参数
        Map<String, String[]> condition = request.getParameterMap();


        CollectionTaskService service=new CollectionTaskServiceImpl();
        PageBean<CollectionTask> pb= null;
        try {
            pb = service.findCollectionTaskByPage(currentPage,rows,condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("/teamTask.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
