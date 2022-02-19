package com.levy.filter;

import com.levy.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author LevyXie
 * @create 2022-01-20 16:59
 * @description
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //此filter为事务的filter，确保发生异常时的事务控制
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollBackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//将异常抛给tomcat服务器
        }
    }

    @Override
    public void destroy() {

    }
}
