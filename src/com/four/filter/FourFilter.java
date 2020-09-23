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
       /* HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();*/
        /*if(uri.contains("/index.jsp")){
            chain.doFilter(req, resp);
        }
        else if(session.getAttribute("teamFilter")==null){

            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else if(session.getAttribute("memberFilter")==null){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else{
            chain.doFilter(req, resp);
        }*/

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
