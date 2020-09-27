package com.four.servlet;

import com.four.entity.*;
import com.four.service.CollectionTaskService;
import com.four.service.TeamMemberService;
import com.four.serviceimpl.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@WebServlet(name = "/FiveServlet",urlPatterns = {"*.do"})
public class FiveServlet extends HttpServlet {
    private CollectionTaskService taskService = new CollectionTaskServiceImpl();
    private TaskFileService fileService = new TaskFileService();
    private TeamMemberService teamMemberService=new TeamMemberServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /*protected void getAllTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        *//*int teamId = Integer.parseInt(request.getParameter("teamId"));*//*
        int teamId = 2;
        List<CollectionTask> list = taskService.getCollectionTaskListByTeamId(teamId);
        request.setAttribute("list", list);
        request.getRequestDispatcher("task.jsp").forward(request, response);
    }*/

    protected void handIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*
        int id = Integer.parseInt(request.getParameter("id"));
*/
        TeamMember member=(TeamMember) request.getSession().getAttribute("member");
        int memberId =member.getId();
        int teamId=member.getTeamId();
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String savePath = this.getServletContext().getRealPath("WEB-INF/upload/"+taskId+teamId);
        System.out.println("save 的调试"+savePath);
        File file = new File(savePath);
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
                response.getWriter().write("错误1");
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
                    System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    filename =member.getNumber()+member.getName()+"."+filename.substring(filename.lastIndexOf(".") + 1);
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
                    Date date = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                    TaskFile taskFile = new TaskFile(memberId,taskId,filename,date);
                    int result = 0;
                    if (fileService.check(memberId)){
                        result = fileService.modifyFileAddressById(taskFile);
                        if (result>0){
                            //request.setAttribute("success",true);
                            response.sendRedirect(request.getContextPath()+"/findCollectionTaskByPageServlet?all=true&success=true");
                            //request.getRequestDispatcher(request.getContextPath()+"/findCollectionTaskByPageServlet?all=true&success=true").forward(request,response);
                        }
                    }
                    else
                    {
                        result = fileService.addTaskFile(taskFile);
                        if (result>0){
                            //request.setAttribute("success",true);
                            //request.getRequestDispatcher(request.getContextPath()+"/findCollectionTaskByPageServlet?all=true").forward(request,response);
                            response.sendRedirect(request.getContextPath()+"/findCollectionTaskByPageServlet?all=true&success=true");
                        }
                    }

                }
            }
        } catch (Exception e) {
//            request.setAttribute("success",false);
//            request.getRequestDispatcher(request.getContextPath()+"/findCollectionTaskByPageServlet?all=true").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/findCollectionTaskByPageServlet?all=true&success=false");
        }

    }


    protected void down(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int memberId =Integer.parseInt(request.getParameter("memberId"));
        int taskId=Integer.parseInt(request.getParameter("taskId"));
        int teamId=((Team)request.getSession().getAttribute("team")).getId();
        String fileName = fileService.getAddressById(memberId,taskId);
 //       fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload/"+taskId+teamId);
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        File file = new File(path + "\\" + fileName);
        System.out.println(file);
        if(!file.exists()){
            response.getWriter().write("file not find");
            return;
        }
        String realname = fileName;//.substring(fileName.indexOf("_")+1);
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        ServletOutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.flush();
        out.close();
        response.getWriter().write("success");
    }
    public String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf; //0--15
        int dir2 = (hashcode&0xf0)>>4; //0-15
        String dir = saveRootPath;
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }



    protected  String downAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Team team=(Team)request.getSession().getAttribute("team");
        int taskId=Integer.parseInt(request.getParameter("taskId"));
        String filePath = this.getServletContext().getRealPath("/WEB-INF/upload/"+taskId+team.getId());
        File dirFile = new File(filePath) ;
        ArrayList<String> allFilePath = this.Dir(dirFile);
        List<File> filesList = new ArrayList<>();

        File[] files=new File[allFilePath.size()];
        String path;
        for (int j = 0; j < allFilePath.size(); j++) {
            path=allFilePath.get(j);
            File file = new File(path);
            files[j]=file;
            filesList.add(file);
        }
//        request.getRequestDispatcher("/showAllStu.do").forward(request, response);
//        response.sendRedirect("/showAllStu.do");
        return	downLoadFiles(filesList,filePath,request,response);
    }
    public ArrayList<String> Dir(File dirFile) throws Exception {
        ArrayList<String> dirStrArr = new ArrayList<String>();
        if (dirFile.exists()) {
            File files[] = dirFile.listFiles();
            for (File file : files) {
                if (dirFile.getPath().endsWith(File.separator)) {
                    dirStrArr.add(dirFile.getPath() + file.getName());
                } else {
                    dirStrArr.add(dirFile.getPath() + File.separator
                            + file.getName());
                }
            }
        }
        return dirStrArr;
    }

    //下载文件
    public  String downLoadFiles(List<File> files,String filePath ,HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //这里的文件你可以自定义是.rar还是.zip
            Team team=(Team)request.getSession().getAttribute("team");

            String realname=team.getName()+""+".zip";
            //filePath = filePath+"/"+"全部作业.zip";
            File file = new File(filePath);
            if (!file.exists()){
                file.createNewFile();
            }else{
                file.delete();
            }
            //response.reset();
            //创建文件输出流
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            ServletOutputStream fous = response.getOutputStream();
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            zipFiles(files, zipOut);
            zipOut.close();
            fous.flush();
            fous.close();
            //SummaryRecord/2/3/1536360821177/2018教学班全部附件.zip
            return filePath;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return "文件下载出错";
    }

    //把接受的全部文件打成压缩包
    public  void zipFiles(List files, ZipOutputStream outputStream) {

        int size = files.size();

        for(int i = 0; i < size; i++) {

            File file = (File) files.get(i);

            zipFile(file, outputStream);
        }
    }
    public  void zipFile(File inputFile,ZipOutputStream ouputStream) {

        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    //关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void showAllStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        Team team=(Team)request.getSession().getAttribute("team");
        int teamId = team.getId();
        List<TeamMember> list = teamMemberService.getTeamMemberListByTeamId(teamId);
        if(list==null) list=new ArrayList<>();
        System.out.println(list.size());
        List<TaskFile> list1 = fileService.getTaskFile(taskId);
        if(list1==null) list1=new ArrayList<>();
        System.out.println(list1.size());
        List<ApplicationTask> tasks = new ArrayList<>();
        for (int i=0; i<list.size();i++){
            boolean flag = false;
            Date date=null;
            int takid=0;
            for (int x=0;x<list1.size();x++){
                if (list.get(i).getId() == list1.get(x).getMemberId()){
                    flag = true;
                    date=list1.get(x).getUploadTime();
                    takid=list1.get(x).getTaskId();
                }
            }
            if (flag) {
                tasks.add(new ApplicationTask(list.get(i).getId(),takid,list.get(i).getNumber(),list.get(i).getName(),date,"",1));
            }else {
                tasks.add(new ApplicationTask(list.get(i).getId(),0,list.get(i).getNumber(),list.get(i).getName(),null,"未提交",0));
            }
        }
        System.out.println(tasks.size());
        int totalCount = teamMemberService.getMemberCount(teamId);
        int upCount = fileService.getFileCount(taskId);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("upCount",upCount);
        request.setAttribute("choose",1);
        request.setAttribute("tasks",tasks);
        request.getRequestDispatcher("taskDetail.jsp").forward(request,response);
    }
    protected void showNotHand(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        Team team=(Team)request.getSession().getAttribute("team");
        int teamId = team.getId();
        List<TeamMember> list = teamMemberService.getTeamMemberListByTeamId(teamId);
        List<TaskFile> list1 = fileService.getTaskFile(taskId);
        List<ApplicationTask> tasks = new ArrayList<>();
        for (int i=0; i<list.size();i++){
            boolean flag = false;
            Date date=null;
            int takid=0;
            for (int x=0;x<list1.size();x++){
                if (list.get(i).getId() == list1.get(x).getMemberId()){
                    flag = true;
                    date=list1.get(x).getUploadTime();
                    takid=list1.get(x).getTaskId();
                }
            }
            if (flag) {
                tasks.add(new ApplicationTask(list.get(i).getId(),takid,list.get(i).getNumber(),list.get(i).getName(),date,"",1));
            }else {
                tasks.add(new ApplicationTask(list.get(i).getId(),0,list.get(i).getNumber(),list.get(i).getName(),null,"未提交",0));
            }
        }
 //       this.showAllStu(request,response);
        request.setAttribute("choose",0);
        request.setAttribute("tasks",tasks);
        int totalCount = teamMemberService.getMemberCount(teamId);
        int upCount = fileService.getFileCount(taskId);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("upCount",upCount);
        request.getRequestDispatcher("taskDetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String servletPath = request.getServletPath();
        /*if (servletPath.contains("jump.do")){
            try {
                getAllTask(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }else */if (servletPath.contains("handIn.do")){
            try {
                handIn(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (servletPath.contains("showAllStu.do")){
            try {
                showAllStu(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (servletPath.contains("down.do")){
            try {
                down(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (servletPath.contains("downAll.do")){
            try {
                downAll(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (servletPath.contains("showNotHand.do")){
            try {
                showNotHand(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
