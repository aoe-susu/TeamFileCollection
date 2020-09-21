package com.four.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;

import com.four.entity.Team;
import com.four.entity.TeamMember;
import com.four.service.ManagerService;
import com.four.service.impl.ManagerServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ManagerServlet", urlPatterns = {"*.do"})
public class ManagerServlet extends HttpServlet {

    private ManagerService managerService = new ManagerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();
        System.out.println(url);
        if (req.getRequestURL().toString().contains("login.do")) {
            login(req, resp);
        }
        if (req.getRequestURL().toString().contains("getCookie.do")) {
            getCookie(req, resp);
        }
        if (req.getRequestURL().toString().contains("getTeam.do")) {
            getTeams(req, resp);
        }
        if (req.getRequestURL().toString().contains("getTotalCount.do")) {
            getTotalCount(req, resp);
        }
        if(req.getRequestURL().toString().contains("getTeamMember.do")){
            getTeamMemebers(req,resp);
        }

        if(req.getRequestURL().toString().contains("deleteTeamMember.do")){
            deleteTeamMember(req,resp);
        }
    }

    private boolean deleteTeamMember(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("deleteTeamMember--------");
        String id = req.getParameter("id");
        int number = managerService.deleteTeamMembers(Integer.parseInt(id));
        try {
            PrintWriter printWriter = resp.getWriter();
            System.out.println("------------"+number);
            printWriter.write(number);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getTeamMemebers(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        List<TeamMember> list = managerService.getTeamMembers(Integer.parseInt(id));
        try {
            PrintWriter printWriter = resp.getWriter();
            String string = new String(JSON.toJSONString(list).getBytes(),"UTF-8");
            System.out.println("------------"+string);
            printWriter.write(string);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getTotalCount(HttpServletRequest req, HttpServletResponse resp){
        int count = managerService.getTotalCounts();
        System.out.println(count+"-------------");
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.print(count);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getTeams(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("-----getTeams------------");
        List<Team> teams = managerService.getTeams();
        try {
            PrintWriter printWriter = resp.getWriter();
            String string = new String(JSON.toJSONString(teams).getBytes(),"UTF-8");
            System.out.println("------------"+string);
            printWriter.write(string);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getCookie(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("cookie----------");
        Cookie[] cookies = req.getCookies();
        String loginpwd = null;
        for (Cookie cookie : cookies) {
            System.out.println(cookie.toString());
            if (cookie.getName().equalsIgnoreCase("loginpwd")) {
                loginpwd = cookie.getValue();
            }
        }
        System.out.println("------loginpwd-----" + loginpwd);
        try {
            resp.getWriter().write(loginpwd);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private boolean login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginuser = req.getParameter("loginuser");
        String loginpwd = req.getParameter("loginpwd");
        String[] check = req.getParameterValues("check");
        PrintWriter writer = resp.getWriter();
        System.out.println("---------check----------" + check[0]);
        System.out.println("------------" + loginuser);
        System.out.println("--------------" + loginpwd);

        if ("admin".equalsIgnoreCase(loginuser) && "111111".equalsIgnoreCase(loginpwd)) {
            writer.append("true");
            if (check != null && check[0].equalsIgnoreCase("checked")) {
                resp.addCookie(new Cookie("loginpwd", loginpwd));
            }
            return true;
        }
        writer.append("false");
        return false;
    }


}
