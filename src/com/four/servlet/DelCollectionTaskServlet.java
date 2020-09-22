package com.four.servlet;

import com.four.service.CollectionTaskService;
import com.four.serviceimpl.CollectionTaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delCollectionTaskServlet")
public class DelCollectionTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CollectionTaskService service=new CollectionTaskServiceImpl();
        try {
            service.deleteCollectionTask(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/findCollectionTaskByPageServlet?currentPage="+request.getParameter("currentPage")+"&rows=3&all="+request.getParameter("all"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
