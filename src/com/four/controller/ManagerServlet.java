package com.four.controller;

import com.alibaba.fastjson.JSON;

import com.four.entity.Team;
import com.four.entity.TeamMember;
import com.four.service.ManagerService;
import com.four.service.impl.ManagerServiceImpl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

import java.util.*;

@WebServlet(name = "ManagerServlet", urlPatterns = {"*.do"})
public class ManagerServlet extends HttpServlet {

    private ManagerService managerService = new ManagerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    private boolean getform(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = resp.getWriter();
            writer.append("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean addTeam(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("addTeam-----------------");

       /* InputStream stream = req.getInputStream();
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(isr);
        String str = br.readLine();
        System.out.println(str);

        str = URLDecoder.decode(str, "utf-8");
        System.out.println(str);
        br.close();*/

        /*  String number = req.getParameter("number");
        String account = req.getParameter("account");
        String team_name = req.getParameter("team_name");
        String check = req.getParameter("check");
        String iurl = req.getParameter("iurl");
       */

        int sizeThreshold = 1024 * 1024;
        File repository = new File("/img");
        Map<String,String> map = new HashMap<>();
        // 创建磁盘文件项工厂，参数为缓存文件大小和临时文件位置
        DiskFileItemFactory factory = new DiskFileItemFactory(sizeThreshold, repository);

        // 创建文件上传核心类
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        // 判断是否是Multipart类型的
        if(ServletFileUpload.isMultipartContent(req)) {
            System.out.println("-----ServletFileUpload.isMultipartContent(req)-------");
            // 解析request并获得文件项集合

            List<FileItem> list = null;
            try {
                list = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if(list != null) {
                for(FileItem item : list) {
                    // 判断参数是普通参数还是文件参数
                    if(item.isFormField()) {
                        // 获得普通参数的key、value（即formData的fileName和fileSize）
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
                        map.put(fieldName,fieldValue);
                        System.out.println("FormField:k=" + fieldName + ",v=" + fieldValue);
                    } else {
                        //获得文件参数（即formData的file）
                        String fileName = item.getName();
                        String path = "/" + fileName;
//                        System.out.println("item----------"+item);
//                        System.out.println("fileName--------------"+fileName);
//                        System.out.println("path------------------"+path);
                        // 上传文件
                        map.put("iurl","img/"+fileName);
                        path ="G:\\Git\\gitpropercy\\TeamFile\\web\\img"+path;

                        uploadFile(item,path);
                    }
                }
            }
        }

        String introduce = map.get("introduce");
        System.out.println("------introduce-------"+introduce);
        Team team = new Team();
        team.setId(Integer.parseInt(map.get("number")));
        team.setAccount(map.get("account"));
        team.setIconUrl(map.get("iurl"));
        team.setIntroduction(introduce);
        team.setName(map.get("team_name"));
        team.setPassword("111111");
        int num = managerService.insertTeam(team);
        try {
            PrintWriter printWriter = resp.getWriter();
            System.out.println("------------" + num);
            printWriter.write(num);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

        /*
        StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) req;

        // 遍历普通参数（即formData的fileName和fileSize）
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            String val = request.getParameter(key);
            System.out.println("FormField：k=" + key + "v=" + val);
        }

        // 遍历文件参数（即formData的file）
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            String fileNames = file.getOriginalFilename();
            // 文件名
            fileNames = new String(fileNames.getBytes("UTF-8"));
            //int split = fileNames.lastIndexOf(".");
            // 文件前缀
            //String fileName = fileNames.substring(0, split);
            // 文件后缀
            //String fileType = fileNames.substring(split + 1, fileNames.length());
            // 文件大小
            //Long fileSize = file.getSize();
            // 文件内容
            byte[] content = file.getBytes();

            FileUtils.writeByteArrayToFile(new File(fileNames), content);
        }

        return "返回给前台的消息";*/
    }

    public static void uploadFile(FileItem item, String targetFilePath) throws IOException {
        InputStream in = item.getInputStream();
        OutputStream out = new FileOutputStream(targetFilePath);

        IOUtils.copy(in, out);

        in.close();
        out.close();

        // 删除临时文件
        item.delete();
    }

    private boolean deleteTeamMember(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("deleteTeamMember--------");
        String id = req.getParameter("id");
        int number = managerService.deleteTeamMembers(Integer.parseInt(id));
        try {
            PrintWriter printWriter = resp.getWriter();
            System.out.println("------------" + number);
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
            String string = new String(JSON.toJSONString(list).getBytes(), "UTF-8");
            System.out.println("------------" + string);
            printWriter.write(string);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    private boolean getTotalCount(HttpServletRequest req, HttpServletResponse resp) {
        int count = managerService.getTotalCounts();
        System.out.println(count + "-------------");
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
            String string = new String(JSON.toJSONString(teams).getBytes(), "UTF-8");
            System.out.println("------------" + string);
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
        if (req.getRequestURL().toString().contains("getTeamMember.do")) {
            getTeamMemebers(req, resp);
        }

        if (req.getRequestURL().toString().contains("deleteTeamMember.do")) {
            deleteTeamMember(req, resp);
        }

        if (req.getRequestURL().toString().contains("addTeam.do")) {
            addTeam(req, resp);
        }
        if (req.getRequestURL().toString().contains("getform.do")) {
            getform(req, resp);
        }
        if (req.getRequestURL().toString().contains("showTeamServlet.do")){
            showTeamServlet(req, resp);
        }

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

    private void showTeamServlet(HttpServletRequest request, HttpServletResponse resp){
        System.out.println("----------------"+request.getParameter("page"));
       // int page = Integer.parseInt(request.getParameter("page"));
        //System.out.println("page ----------"+page);
    }

}
