package com.four.servlet;

import com.alibaba.fastjson.JSON;
import com.four.entity.Team;
import com.four.entity.TeamMember;
import com.four.service.TeamMemberService;
import com.four.service.TeamService;
import com.four.serviceimpl.TeamMemberServiceImpl;
import com.four.serviceimpl.TeamServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@WebServlet(name = "TeamServlet", urlPatterns = {"*.action"})
public class TeamServlet extends HttpServlet {
    TeamService teamService = new TeamServiceImpl();
    TeamMemberService teamMemberService = new TeamMemberServiceImpl();
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
        String varifycode = request.getParameter("varifycode");

        if ("".equals(team.getAccount())   || "".equals(team.getName()) || "".equals(team.getPassword()) || "".equals(team.getIntroduction()) ||"".equals( varifycode)) {
            response.sendRedirect(request.getContextPath()+"/creatTeam.jsp?register_msg=HasEmptyInput");
            return;
        }
        boolean result = teamService.existTeamAccount(team.getAccount());
        if (result == true) {
            response.sendRedirect(request.getContextPath()+"/creatTeam.jsp?register_msg=AccountExisted");
            return;
        }
        //校验验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(varifycode)) {
            //验证码不正确
            //提示信息
            response.sendRedirect(request.getContextPath()+"/creatTeam.jsp?register_msg=ValifyCodeError");
            return;
        }

        if (teamService.addTeam(team) > 0) {
            team=teamService.getTeamByAccount(team.getAccount());
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+team.getId());
        } else {
            response.sendRedirect(request.getContextPath()+"/creatTeam.jsp?register_msg=CreateFail");
        }
    }

    //检测账号是否存在
    protected void validateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    //修改密码时验证旧密码是否正确
    protected void validatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String oldPassword = request.getParameter("oldPassword");
        Team team =(Team)request.getSession().getAttribute("team");


        if (team.getPassword().equals(oldPassword)) {
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
        int teamId=Integer.parseInt(request.getParameter("teamId"));
        Team team = teamService.getTeamById(teamId);
        if ("".equals(account) || "".equals(password)) {
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=AccountOrPasswordEmpty");
            return;
        }

        //校验验证码
        String varifycode = request.getParameter("varifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null) {
            if (!session.getAttribute("status").equals(1)) {
                return;
            }
        } else if (!checkcode_server.equalsIgnoreCase(varifycode)) {
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=VarifyCodeError");
            return;
        }

        boolean result =team!=null&&team.getAccount().equals(account)&&team.getPassword().equals(password);

        if (result) {
            //登录成功
            session.setAttribute("status", 1);
            session.setAttribute("team", team);
            session.setAttribute("filter","team");
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=LoginSuccess");
        }
        else{
            TeamMember teamMember=teamMemberService.getMemberByPwdAndNum(password,account,teamId);
            if(teamMember!=null){
                //登录成功
                session.setAttribute("status", 1);
                session.setAttribute("team", team);
                session.setAttribute("member",teamMember);
                session.setAttribute("filter","member");
                response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=LoginSuccess");
            }
            else{
                //登录失败
                response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=AccountOrPasswordError");
            }

        }

    }


    //通过id获取team
    protected void getTeamById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html;charset=utf-8");
        Team team = teamService.getTeamById(id);
        if (team != null) {

            String json = JSON.toJSONString(team);
            //把json格式输出到前端
            response.getWriter().print(json);
        }
    }

   /* //通过account获取team
    protected void getTeamByAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object account = request.getSession().getAttribute("account");

        if (team != null) {

            // response.sendRedirect("admin/teamList.jsp");
            //request.getRequestDispatcher("admin/teamList.jsp").forward(request, response);

        }

    }*/

    //通过name获取team
    protected void getTeamByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Team team = teamService.getTeamByAccount(name);
        request.setAttribute("team", team);
        request.getRequestDispatcher("admin/teamList.jsp").forward(request, response);

    }

    //修改团队的密码
    protected void modifyTeamPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Team team=(Team) request.getSession().getAttribute("team");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        int result = teamService.modifyPasswordById(team.getId(), newPassword);
        if (result > 0) {
            request.getSession().setAttribute("team",null);
            request.getSession().setAttribute("filter","visitor");
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+team.getId()+"&msg=Logout");
        }
    }

    //修改团队的名称
    protected void modifyTeamName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Team team=(Team) request.getSession().getAttribute("team");
        String teamName = request.getParameter("name");

        int result = teamService.modifyNameById(team.getId(), teamName);
        if (result > 0) {
            team.setName(teamName);
            request.getSession().setAttribute("team",team);
            String data = "true " + team.getId() + " " + teamName;
            response.getWriter().write(data);
        } else {
            response.getWriter().write("false");
        }
    }

    //修改团队的简介
    protected void modifyTeamIntroduction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Team team=(Team)request.getSession().getAttribute("team");
        String teamIntroduction = request.getParameter("introduction");
        int result = teamService.modifyIntroductionById(team.getId(), teamIntroduction);
        if (result > 0) {
            team.setIntroduction(teamIntroduction);
            request.getSession().setAttribute("team",team);
            String data = "true " + team.getId() + " " + teamIntroduction;
            response.getWriter().write(data);
        } else {
            response.getWriter().print("false");
        }
    }

    //修改团队的图标
    protected void modifyTeamIconUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String savePath = this.getServletContext().getRealPath("img/headImage");
        System.out.println("savePath:   " + savePath);
        File file = new File(savePath);
        System.out.println("file:   " + file);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            file.mkdir();
        }
        String message = "";
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            if (!ServletFileUpload.isMultipartContent(request)) {
                response.getWriter().print("false");
                return;
            }
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    System.out.println(name + "=" + value);
                } else {
                    String filename = item.getName();
                    System.out.println("filename:    " + filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    System.out.println("这是filename的对象： " + filename);
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    message = "文件上传成功！";
                    int result = 0;
                    HttpSession session = request.getSession();
                    if("team".equals(request.getSession().getAttribute("filter"))){
                        Team team = (Team) session.getAttribute("team");
                        result = teamService.modifyIconUrlById(team.getId(), "img/headImage/" + filename);
                        if (result > 0) {
                            team.setIconUrl("img/headImage/" + filename);
                            session.setAttribute("team", team);
                            response.getWriter().write("<script>var url=document.referrer;location.href=url;</script>");
                        }
                    }
                    else if("member".equals(request.getSession().getAttribute("filter"))){
                        TeamMember member=(TeamMember) session.getAttribute("member");
                        result=teamMemberService.modifyIconUrlById(member.getId(),"img/headImage/" + filename);
                        if (result > 0) {
                            member.setIconUrl("img/headImage/" + filename);
                            session.setAttribute("member", member);
                            response.getWriter().write("<script>var url=document.referrer;location.href=url;</script>");
                        }
                    }
                    if(result==0){
                        response.getWriter().print("false");
                    }
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();
        }
        //request.setAttribute("message", message);
    }


    //设置验证码
    protected void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        //在内存中创建一个长80，宽33的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 33;
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


    protected void getIntroduction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId=Integer.parseInt(request.getParameter("teamId"));
        String msg=request.getParameter("msg");
        System.out.println(teamId);
        String filter=request.getSession().getAttribute("filter").toString();
        //---测试
        //String filter="team";
        //request.getSession().setAttribute("team",teamService.getTeamById(teamId));
        //---测试
        Team team=(Team)request.getSession().getAttribute("team");
        if(team!=null&&teamId!=team.getId()){
            filter="visitor";
        }
        if(filter=="visitor")  {
            team=teamService.getTeamById(teamId);
        }

        if(team!=null){
            request.setAttribute("msg",msg);
            request.setAttribute("filter",filter);
            request.setAttribute("team",team);
            //response.sendRedirect(request.getContextPath()+"/teamIntroduction.jsp?teamId="+teamId);
            request.getRequestDispatcher("teamIntroduction.jsp").forward(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.contains("register.action")) { //团队注册
            register(request, response);
        } else if (servletPath.contains("validateAccount.action")) { //验证账号的唯一性
            validateAccount(request, response);
        } else if (servletPath.contains("validatePassword.action")) {  //验证旧密码是否正确
            validatePassword(request, response);
        } else if (servletPath.contains("login.action")) {  //登录
            login(request, response);
        } else if (servletPath.contains("checkCode.action")) { //设计验证码
            checkCode(request, response);
        } else if (servletPath.contains("getTeamById.action")) {  //通过id获取team
            getTeamById(request, response);
        } else if (servletPath.contains("getTeamByName.action")) {  //通过name获取team
            getTeamByName(request, response);
        }/* else if (servletPath.contains("getTeamByAccount.do")) {  //通过account获取team
            getTeamByAccount(request, response);
        }*/ else if (servletPath.contains("modifyTeamPassword.action")) {  //修改团队密码
            modifyTeamPassword(request, response);
        } else if (servletPath.contains("modifyTeamName.action")) { //修改团队名称
            modifyTeamName(request, response);
        } else if (servletPath.contains("modifyTeamIntroduction.action")) { //修改团队简介
            modifyTeamIntroduction(request, response);
        } else if (servletPath.contains("modifyTeamIconUrl.action")) { //修改团队头像
            modifyTeamIconUrl(request, response);
        }else if(servletPath.contains("getIntroduction.action")){
            getIntroduction(request,response);
        }
    }
}