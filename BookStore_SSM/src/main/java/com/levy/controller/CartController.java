package com.levy.controller;

import com.google.gson.Gson;
import com.levy.domain.Book;
import com.levy.domain.Cart;
import com.levy.domain.CartItem;
import com.levy.service.BookService;
import com.levy.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 14:37
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/addItem")
    protected String addItem(HttpServletRequest request,
                             @RequestHeader(value = "Referer",required = false) final String referer){
        String id = request.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice().doubleValue());
        cart.addItem(cartItem);
        //将最后添加的图书至于session中，供ajax进行页面局部刷新使用
        request.getSession().setAttribute("lastAdd", cartItem.getName());
//        System.out.println(cartItem.getName());
        return "redirect:" + referer;
    }

    @RequestMapping("/deleteItem")
    protected String deleteItem(HttpServletRequest request,
                                @RequestHeader(value = "referer",required = false) final String referer){
        String id = request.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        cart.deleteItem(WebUtils.parseInt(id, 0));

        return "redirect:" + referer;
    }

    @RequestMapping("/clear")
    protected String clear(HttpServletRequest request,
                           @RequestHeader(value = "referer",required = false) final String referer){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.clear();

        return "redirect:" + referer;
    }
    @RequestMapping("/updateCount")
    protected String updateCount(HttpServletRequest request,
                                 @RequestHeader(value = "referer",required = false) final String referer){
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        cart.updateCount(WebUtils.parseInt(id, 0), WebUtils.parseInt(count, 0));

        return "redirect:/cart/cart";
    }

}
