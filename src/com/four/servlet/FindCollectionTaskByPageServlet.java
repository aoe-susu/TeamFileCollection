package com.four.servlet;

import com.four.entity.CollectionTask;
import com.four.entity.PageBean;
import com.four.entity.Team;
import com.four.service.CollectionTaskService;
import com.four.serviceimpl.CollectionTaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            rows="4";
        }
        //获取条件查询的参数
        String all=request.getParameter("all");
        Map<String, String[]> temp = request.getParameterMap();
        Map<String, String[]> condition=new HashMap<String, String[]>();
        for (String s : temp.keySet()) {
            if(!"all".equals(s)&&!"success".equals(s)){
                condition.put(s,temp.get(s));
            }
        }
        CollectionTaskService service=new CollectionTaskServiceImpl();
        PageBean<CollectionTask> pb= null;
        try {
            Team team=(Team)request.getSession().getAttribute("team");
            if("true".equals(all)){
                pb = service.findCollectionTaskByPage(team.getId(),currentPage, rows, condition);
            }
            else{
                pb = service.findCollectionTaskByPageAfterTime(team.getId(),currentPage,rows,condition,new Date());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pb",pb);
        if("true".equals(all)){
            request.setAttribute("ft",null);
        }
       else{
            request.setAttribute("ft","time");
        }
        request.setAttribute("condition",condition);
        System.out.println(request.getParameter("success"));
        request.setAttribute("msg",request.getParameter("success"));
        request.getRequestDispatcher("/teamTask.jsp?all="+all).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
