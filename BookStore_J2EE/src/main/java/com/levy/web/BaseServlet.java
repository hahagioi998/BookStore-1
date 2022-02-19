package com.levy.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author LevyXie
 * @create 2022-01-14 0:10
 * @description
 */
public abstract class BaseServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置页面回显字符集，避免乱码
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        try {
            //使用反射获取用户的操作，以上正是hidden属性的使用之处
            Method declaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
