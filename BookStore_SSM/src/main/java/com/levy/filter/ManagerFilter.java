package com.levy.filter;


import com.levy.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author LevyXie
 * @create 2022-01-20 15:41
 * @description
 */
public class ManagerFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    //此filter主要过滤管理员（user.name = "admin" 时可进入管理员后台，否则错误）
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else if("admin".equals(user.getUsername())){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            httpServletRequest.getRequestDispatcher("/pages/error/ErrorDefine.jsp").forward(servletRequest,servletResponse);
        }
    }

    public void destroy() {
    }
}
