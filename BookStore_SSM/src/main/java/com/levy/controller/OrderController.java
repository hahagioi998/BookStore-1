package com.levy.controller;

import com.levy.domain.Cart;
import com.levy.domain.Order;
import com.levy.domain.User;
import com.levy.service.BookService;
import com.levy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 14:46
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/createOrder")
    protected String createOrder(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/pages/user/login.jsp";
        }else{
            Integer userId = user.getId();
            String orderId = orderService.createOrder(cart, userId);
            session.setAttribute("orderId", orderId);
            return "redirect:/pages/cart/checkout.jsp";
        }
    }
}
