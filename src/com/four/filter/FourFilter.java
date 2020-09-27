package com.four.filter;

import com.four.entity.Team;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FourFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        Object filobj=session.getAttribute("filter");
        if(filobj==null) session.setAttribute("filter","visitor");
//        String filter=session.getAttribute("filter").toString();
//        if(uri.contains("/creatTeam.jsp")|| uri.contains("/getIntroduction.action")||uri.contains(".jpg")||uri.contains(".png")||uri.contains(".gif")){
//            chain.doFilter(req, resp);
//            return;
//        }
//        else if("team".equals(filter)){
//            if(
//                    uri.contains("/findCollectionTaskByPageServlet")||
//                    uri.contains("/getAllMember.fuc")||
//                    uri.contains("/getApplyMember.fuc")||
//                    uri.contains("/showAllStu.do")||
//                    uri.contains("/showNotHand.do")||
//                    uri.contains("/down.do")||
//                    uri.contains("/downAll.do")||
//                    uri.contains("/modifyTeamIconUrl.action")||
//                    uri.contains("/modifyTeamIntroduction.action")||
//                    uri.contains("/modifyTeamName.action")||
//                    uri.contains("/modifyTeamPassword.action")
//            ){
//                chain.doFilter(req, resp);
//                return;
//            }
//            else {
//                response.getWriter().write("Error");
//                return;
//            }
//
//        }
//        else if("member".equals(filter)){
//            if(
//                    uri.contains("/findCollectionTaskByPageServlet")||
//                    uri.contains("/modifyTeamIconUrl.action")
//            ){
//                chain.doFilter(req, resp);
//                return;
//            }
//            else {
//                response.getWriter().write("Error");
//                return;
//            }
//        }
//        else{
//            chain.doFilter(req, resp);
//        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
