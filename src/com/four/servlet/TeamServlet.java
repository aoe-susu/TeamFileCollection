package com.four.servlet;

import com.four.entity.Team;
import com.four.service.TeamService;
import com.four.serviceImpl.TeamServiceImpl;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "TeamServlet", urlPatterns = {"*.do"})
public class TeamServlet extends HttpServlet {
    TeamService teamService = new TeamServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    //注册
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Team team = new Team();
        team.setAccount(request.getParameter("account"));
        team.setName(request.getParameter("name"));
        team.setPassword(request.getParameter("password"));
        team.setIntroduction(request.getParameter("introduction"));
        team.setIconUrl("img/1.gif");
        //校验验证码
        String varifycode = request.getParameter("varifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(varifycode)) {
            //验证码不正确
            //提示信息
            request.setAttribute("register_msg", "验证码错误！");
            //跳转注册页面
            request.getRequestDispatcher("admin/teamRegister.jsp").forward(request, response);
        }

        if (teamService.addTeam(team) > 0) {
            request.getRequestDispatcher("admin/teamLogin.jsp").forward(request, response);
        } else {
            response.sendRedirect("admin/teamRegister.jsp");
        }
    }

    //检测账号是否存在
    protected void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String account = request.getParameter("account");
        if (teamService.existTeamAccount(account)) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    //登录
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        if (account == "" || password == "") {
            request.setAttribute("teamLogin_msg", "账号或密码为空,请重新输入！");
            request.getRequestDispatcher("admin/teamLogin.jsp").forward(request, response);
            return;
        }

        //校验验证码
        String varifycode = request.getParameter("varifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(varifycode)) {
            //验证码不正确
            //提示信息
            request.setAttribute("teamLogin_msg", "验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("admin/teamLogin.jsp").forward(request, response);
            return;
        }

        boolean result = teamService.existTeamAccountAndPassword(account, password);
        if (result == true) {
            //登录成功
            session.setAttribute("account", account);
            session.setAttribute("password", password);
            response.sendRedirect("index.jsp");
        } else {
            //登录失败
            request.setAttribute("teamLogin_msg", "账号或密码错误！");
            request.getRequestDispatcher("admin/teamLogin.jsp").forward(request, response);
        }
    }


    //通过id获取team
    protected void getTeamById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        Team team = teamService.getTeamById(id);
        request.setAttribute("team",team);
        request.getRequestDispatcher("admin/updateBaseInfo.jsp").forward(request,response);
    }

    //修改团队的头像、名称、简介
    protected void modifyTeamBaseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //修改团队的密码
    protected void modifyTeamPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //删除
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = teamService.deleteTeamById(id);
        if(result>0){
            response.sendRedirect("queryall.do");
        }
    }

    //设置验证码
    protected void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0, 0, width, height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体", Font.BOLD, 24));
        //向图片上写入验证码
        g.drawString(checkCode, 15, 25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

    private String getCheckCode() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.contains("register.do")) { //注册
            register(request, response);
        } else if (servletPath.contains("validate.do")) { //账号的唯一性
            validate(request, response);
        } else if (servletPath.contains("login.do")) {  //登录
            login(request, response);
        } else if (servletPath.contains("checkCode.do")) { //设计验证码
            checkCode(request, response);
        } else if (servletPath.contains("delete.do")) { //删除
            delete(request, response);
        } else if(servletPath.contains("getTeamById.do")) {  //通过id获取team
            getTeamById(request,response);
        } else if (servletPath.contains("modifyTeamBaseInfo.do")) { //修改基本信息
            modifyTeamBaseInfo(request, response);
        }else if(servletPath.contains("modifyTeamPassword.do")){  //修改密码
            modifyTeamPassword(request,response);
        }
    }


}