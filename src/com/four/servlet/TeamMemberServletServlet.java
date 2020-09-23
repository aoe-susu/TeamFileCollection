package com.four.servlet;

import com.alibaba.fastjson.JSON;
import com.four.entity.Application;
import com.four.entity.TeamMember;
import com.four.service.TeamMemberService;
import com.four.service.impl.TeamMemberServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TeamMemberServletServlet", urlPatterns = {"*.do"})
public class TeamMemberServletServlet extends HttpServlet {
    TeamMemberService teamMemberService = new TeamMemberServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void existMemberNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String num = request.getParameter("num");
        if (teamMemberService.existMemberNumber(num)) {
            response.getWriter().print("true");
        } else {

            response.getWriter().print("false");

        }
    }

    protected void delete1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        teamMemberService.deleteMemberById(id);
        request.getRequestDispatcher("/getAllMember.do").forward(request, response);
    }

    protected void delete2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        teamMemberService.deleteMemberById1(id);
        request.getRequestDispatcher("/getApplyMember.do").forward(request, response);

    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");

        TeamMember teamMember=new TeamMember();
        int id = Integer.parseInt(request.getParameter("id"));
        teamMember.setId(id);
        teamMember.setNumber(request.getParameter("number"));
        teamMember.setName(request.getParameter("name"));
        teamMember.setTeamId(Integer.parseInt(request.getParameter("teamId")));

        teamMember.setIconUrl("321.jpg");
        teamMemberService.addMember(teamMember);
        teamMemberService.deleteMemberById1(id);
        request.getRequestDispatcher("/getAllMember.do").forward(request, response);

    }
    protected void add1(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");

        //Map<String,String[]> map=request.getParameterMap();
        //TeamMember teamMember = new TeamMember();

       /* try {
            BeanUtils.populate(teamMember, map);

            System.out.println(map);



            if(teamMemberService.addMember1(teamMember)>0) {
                request.getRequestDispatcher("getApplyMember.do").forward(request, response);
            } else{
                response.sendRedirect("addTeam.jsp");
            }
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        Application application = new Application();
        application.setNumber(request.getParameter("num"));
        application.setName(request.getParameter("name"));
        application.setTeamId(3);
        if(teamMemberService.addMember1(application)>0) {
            request.getRequestDispatcher("getApplyMember.do").forward(request, response);
        } else{
            response.sendRedirect("addTeam.jsp");
        }

    }

    protected void getAllMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // int teamId=Integer.parseInt(request.getParameter("teamId"));
        int teamId = 2;
        List<TeamMember> teamMember = null;
        try {
            teamMember = teamMemberService.getMemberListByTeamId(teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("teamMember", teamMember);
        request.getRequestDispatcher("queryMember.jsp").forward(request, response);
    }

    protected void getApplyMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = 2;
        List<TeamMember> teamMember = null;
        try {
            teamMember = teamMemberService.getApplyMemberListByteamId(teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(teamMember.toString());
        request.setAttribute("teamMember", teamMember);
        request.getRequestDispatcher("queryAply.jsp").forward(request, response);
    }

    protected void getMemberByPwdAndNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");



        response.setContentType("text/html;charset=utf-8");
        String pwd = request.getParameter("pwd");
        String num = request.getParameter("num");

        System.out.println("pwd  ="+pwd);
        System.out.println("num  ="+num);

        if (teamMemberService.getMemberByPwdAndNum(pwd,num)>0) {



            HttpSession session = request.getSession();
            session.setAttribute("num",num);

            System.out.println("session   ="+request.getSession().getAttribute("num"));

            response.getWriter().print("true");
        } else {

            response.getWriter().print("false");

        }
    }

    protected void getTeamMemberById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("textml;charset=utf-8");
        TeamMember teamMember = teamMemberService.getTeamById(id);
        if (teamMember != null) {
            request.setAttribute("teamMember", teamMember);
            String json = JSON.toJSONString(teamMember);
            //把json格式输出到前端
            response.getWriter().print(json);
        }
    }

    protected void modifyIconUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*TeamMember teamMember = (TeamMember) request.getSession().getAttribute("teamMember");
        int id = teamMember.getId();// Integer.parseInt(request.getParameter("id"));*/
        TeamMember teamMember = new TeamMember();
        String num = (String) request.getSession().getAttribute("num");

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
                    result = teamMemberService.modifyIconUrlByNumber(num, "img/headImage/" + filename);
                    System.out.println("这是result的值" + result);
                    if (result > 0) {
                        teamMember.setIconUrl("img/headImage/" + filename);
                        request.getSession().setAttribute("teamMember", teamMember);
                        response.sendRedirect(request.getContextPath() + "/queryMember.jsp");
                    } else {
                        response.getWriter().print("false");
                    }
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();
        }
        request.setAttribute("message", message);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.contains("existMemberNumber.do")) {
            existMemberNumber(request, response);
        }
        if (servletPath.contains("delete1.do")) {
            delete1(request, response);
        }
        if (servletPath.contains("delete2.do")) {
            delete2(request, response);
        }
        if (servletPath.contains("add.do")) {
            add(request, response);
        }
        if (servletPath.contains("add1.do")) {
            add1(request, response);
        }
        if (servletPath.contains("getAllMember.do")) {
            getAllMember(request, response);
        }

        if (servletPath.contains(("getApplyMember.do"))) {
            getApplyMember(request, response);
        }
        if (servletPath.contains(("getMemberByPwdAndNum.do"))) {
            getMemberByPwdAndNum(request, response);
        }
        if (servletPath.contains(("getTeamMemberById.do"))) {
            getTeamMemberById(request, response);
        }
        if (servletPath.contains(("modifyIconUrl.do"))) {
            modifyIconUrl(request, response);
        }
    }
}
