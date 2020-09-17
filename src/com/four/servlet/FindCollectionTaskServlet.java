package com.four.servlet;

import com.four.entity.CollectionTask;
import com.four.service.CollectionTaskService;
import com.four.serviceimpl.CollectionTaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/findCollectionTaskServlet")
public class FindCollectionTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CollectionTaskService service=new CollectionTaskServiceImpl();
        CollectionTask task= null;
        try {
            task = service.findCollectionTaskById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("task",task);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
