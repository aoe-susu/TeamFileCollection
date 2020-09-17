package com.four.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ManagerServlet", urlPatterns = {"*.do"})
public class ManagerServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();
        System.out.println(url);
        if(req.getRequestURL().toString().contains("login.do")){
            login(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    private boolean login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginuser = req.getParameter("loginuser");
        String loginpwd = req.getParameter("loginpwd");
        String[] check = req.getParameterValues("check");
        PrintWriter writer = resp.getWriter();
        System.out.println("---------check----------"+check[0]);
        System.out.println("------------"+loginuser);
        System.out.println("--------------"+loginpwd);
        if(check != null){
            resp.addCookie(new Cookie("loginpwd",loginpwd));
        }
        if("admin".equalsIgnoreCase(loginuser)&&"111111".equalsIgnoreCase(loginpwd)){
            writer.append("true");
            return true;
        }
           writer.append("false");
        return false;
    }
}
