package com.four.servlet;

import com.four.entity.CollectionTask;
import com.four.service.CollectionTaskService;
import com.four.serviceimpl.CollectionTaskServiceImpl;
import com.mchange.v2.beans.BeansUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/addCollectionTaskServlet")
public class AddCollectionTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        /*for (String[] data: map.values()){
            for (int i=0;i<data.length;i++){
                System.out.print(data[i]);
            }
            System.out.println();
        }*/
        CollectionTask task=new CollectionTask();
        try {
            ConvertUtils.register(new Converter() {//注册一个日期转换器

                public Object convert(Class type, Object value) {
                    Date date1 =null;
                    if(value instanceof String){
                        String date = (String) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date1 = sdf.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    return date1;
                }
            }, Date.class);
            BeanUtils.populate(task,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        CollectionTaskService service=new CollectionTaskServiceImpl();
        try {
            service.addTask(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/findCollectionTaskByPageServlet?currentPage="+request.getParameter("currentPage")+"&rows=3&all="+request.getParameter("all"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
