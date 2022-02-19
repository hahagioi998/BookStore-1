package com.levy.controller;

import com.google.gson.Gson;
import com.levy.domain.User;
import com.levy.mapper.UserMapper;
import com.levy.service.UserService;
import com.levy.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 14:28
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    protected String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.userLogin(new User(null, username, password, null));
        if(user == null){
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            return "user/login";
        }else{
            request.getSession().setAttribute("user", user);
            return "/user/login_success";
        }
    }

    @RequestMapping("/regist")
    protected String regist(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        request.setAttribute("username",username);
        request.setAttribute("email",email);
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if(token != null && token.equalsIgnoreCase(code)){
            if(userService.existsUsername(username)) {
                //数据库查询不到该用户名即为true
                userService.registerUser(user);
                return "/user/regist_success";
            }else{
                //数据库查询到该用户名即为false
                request.setAttribute("msg","用户名[" + username + "]已存在！");
                return "regist";
            }
        }else{
            request.setAttribute("msg","验证码错误！");
            return "regist";
        }
    }

    @RequestMapping("/logout")
    protected String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/client/page";
    }

    @RequestMapping("/ajaxExistUsername")
    @ResponseBody
    protected String ajaxExistUsername(HttpServletRequest request){
        String username = request.getParameter("username");
        boolean isExist = userService.existsUsername(username);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("isExist", isExist);

        Gson gson = new Gson();

        String userStatus = gson.toJson(resultMap);
        return userStatus;
    }

}
