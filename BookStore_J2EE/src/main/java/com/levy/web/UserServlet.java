package com.levy.web;

import com.google.gson.Gson;
import com.levy.pojo.User;
import com.levy.service.UserService;
import com.levy.service.impl.UserServiceImpl;
import com.levy.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author LevyXie
 * @create 2022-01-13 23:31
 * @description
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.userLogin(new User(null, username, password, null));
        if(loginUser == null){
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        req.setAttribute("username",username);
        req.setAttribute("email",email);
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if(token != null && token.equalsIgnoreCase(code)){
            if(userService.existsUsername(username)) {
                //数据库查询不到该用户名即为true
                userService.registerUser(user);
                resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
            }else{
                //数据库查询到该用户名即为false
                req.setAttribute("msg","用户名[" + username + "]已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        }else{
            req.setAttribute("msg","验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        //此处若不加/index页面跳转有误
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean isExist = userService.existsUsername(username);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isExist", isExist);

        Gson gson = new Gson();

        String userStatus = gson.toJson(resultMap);
        resp.getWriter().write(userStatus);
    }
}
