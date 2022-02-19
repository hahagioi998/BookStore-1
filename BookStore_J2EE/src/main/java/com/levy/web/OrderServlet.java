package com.levy.web;

import com.levy.pojo.Cart;
import com.levy.pojo.User;
import com.levy.service.BookService;
import com.levy.service.OrderService;
import com.levy.service.impl.BookServiceImpl;
import com.levy.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LevyXie
 * @create 2022-01-19 22:42
 * @description
 */
public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();

    BookService bookService = new BookServiceImpl();

    //创建订单的功能
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if( user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }else {
            Integer userId = user.getId();

            String orderId = orderService.createOrder(cart, userId);

            req.getSession().setAttribute("orderId",orderId );
            resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
        }
    }
}
