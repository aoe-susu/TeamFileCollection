package com.four.servlet;

import com.alibaba.fastjson.JSON;
import com.four.entity.Application;
import com.four.entity.Team;
import com.four.entity.TeamMember;
import com.four.service.ApplicationService;
import com.four.service.TeamMemberService;
import com.four.service.TeamService;
import com.four.serviceimpl.ApplicationServiceImpl;
import com.four.serviceimpl.TeamMemberServiceImpl;
import com.four.serviceimpl.TeamServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TeamMemberServletServlet", urlPatterns = {"*.fuc"})
public class TeamMemberServletServlet extends HttpServlet {
    TeamMemberService teamMemberService = new TeamMemberServiceImpl();
    ApplicationService applicationService=new ApplicationServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void existMemberNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String num = request.getParameter("num");
        if (teamMemberService.existApplicationNumber(num)) {
            response.getWriter().print("true");
        } else {

            response.getWriter().print("false");

        }
    }

    protected void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        teamMemberService.deleteMemberById(memberId);
        response.sendRedirect(request.getContextPath()+"/getAllMember.fuc?msg=deleteSuccess");
    }

    protected void deleteApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        applicationService.deleteApplicationId(applicationId);
        response.sendRedirect(request.getContextPath()+"/getApplyMember.fuc?msg=deleteSuccess");

    }

    protected void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.setCharacterEncoding("utf-8");

        TeamMember teamMember=new TeamMember();
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        Team team=(Team) request.getSession().getAttribute("team");
        int teamId = team.getId();
        Application application=applicationService.getApplication(applicationId);

        teamMember.setName(application.getName());
        teamMember.setNumber(application.getNumber());
        teamMember.setTeamId(application.getTeamId());
        teamMember.setIconUrl("img/1.gif");

        teamMemberService.addMember(teamMember);
        applicationService.deleteApplicationId(application.getId());
        response.sendRedirect(request.getContextPath()+"/getApplyMember.fuc?msg=agreeSuccess");

    }
    protected void addApplication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        int teamId=Integer.parseInt(request.getParameter("teamId"));
        Application application = new Application();
        application.setNumber(request.getParameter("num"));
        application.setName(request.getParameter("name"));
        application.setTeamId(teamId);
        if(applicationService.addApplication(application)>0) {
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=ApplySuccess");
        } else{
            response.sendRedirect(request.getContextPath()+"/getIntroduction.action?teamId="+teamId+"&msg=ApplyFail");
        }

    }

    protected void getAllMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // int teamId=Integer.parseInt(request.getParameter("teamId"));
        Team team=(Team) request.getSession().getAttribute("team");
        int teamId = team.getId();
        List<TeamMember> list = null;
        try {
            list = teamMemberService.getMemberListByTeamId(teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("list", list);
        request.setAttribute("msg",request.getParameter("msg"));
        request.getRequestDispatcher("teamMember.jsp").forward(request, response);
    }

    protected void getApplyMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Team team=(Team) request.getSession().getAttribute("team");
        int teamId = team.getId();
        List<Application> list = null;
        try {
            list = applicationService.getApplyApplicationListByteamId(teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(teamMember.toString());
        request.setAttribute("list", list);
        request.setAttribute("msg",request.getParameter("msg"));
        request.getRequestDispatcher("teamApplication.jsp").forward(request, response);
    }

//    protected void getMemberByPwdAndNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//
//
//
//        response.setContentType("text/html;charset=utf-8");
//        String pwd = request.getParameter("pwd");
//        String num = request.getParameter("num");
//
//        System.out.println("pwd  ="+pwd);
//        System.out.println("num  ="+num);
//
//        if (teamMemberService.getMemberByPwdAndNum(pwd,num)>0) {
//
//
//
//            HttpSession session = request.getSession();
//            session.setAttribute("num",num);
//
//            System.out.println("session   ="+request.getSession().getAttribute("num"));
//
//            response.getWriter().print("true");
//        } else {
//
//            response.getWriter().print("false");
//
//        }
//    }

    protected void getTeamMemberById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("textml;charset=utf-8");
        TeamMember teamMember = teamMemberService.getTeamMemberById(id);
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
        if (servletPath.contains("existMemberNumber.fuc")) {
            existMemberNumber(request, response);
        }
        if (servletPath.contains("deleteMember.fuc")) {
            deleteMember(request, response);
        }
        if (servletPath.contains("deleteApplication.fuc")) {
            deleteApplication(request, response);
        }
        if (servletPath.contains("addMember.fuc")) {
            addMember(request, response);
        }
        if (servletPath.contains("addApplication.fuc")) {
            addApplication(request, response);
        }
        if (servletPath.contains("getAllMember.fuc")) {
            getAllMember(request, response);
        }

        if (servletPath.contains(("getApplyMember.fuc"))) {
            getApplyMember(request, response);
        }
//        if (servletPath.contains(("getMemberByPwdAndNum.fuc"))) {
//            getMemberByPwdAndNum(request, response);
//        }
        if (servletPath.contains(("getTeamMemberById.fuc"))) {
            getTeamMemberById(request, response);
        }
        if (servletPath.contains(("modifyIconUrl.fuc"))) {
            modifyIconUrl(request, response);
        }
    }
}
