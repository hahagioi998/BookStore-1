package com.levy.web;

import com.google.gson.Gson;
import com.levy.pojo.Book;
import com.levy.pojo.Cart;
import com.levy.pojo.CartItem;
import com.levy.service.BookService;
import com.levy.service.impl.BookServiceImpl;
import com.levy.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LevyXie
 * @create 2022-01-16 23:33
 * @description 用户的购物车操作Servlet
 */
public class CartServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice().doubleValue());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastAdd", cartItem.getName());
        //利用Referer重定向回商品所在的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    //采用ajax技术对页面局部进行刷新
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice().doubleValue());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastAdd", cartItem.getName());
        //以下为利用ajax请求实现页面的局部更新
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", cart.getTotalCount());
        map.put("lastAdd", cartItem.getName());
        Gson gson = new Gson();
        String resultJson = gson.toJson(map);

        resp.getWriter().write(resultJson);
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        //利用Referer重定向回商品所在的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        req.getSession().setAttribute("cart", cart);
        cart.clear();
        //利用Referer重定向回商品所在的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.updateCount(id,count);
        //重定向返回购物车页面
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }
}
