package com.levy.controller;

import com.github.pagehelper.PageInfo;
import com.levy.domain.Book;
import com.levy.service.BookService;
import com.levy.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 16:26
 */
@Controller
@RequestMapping("/client")
public class ClientBookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/page")
    protected String page(HttpServletRequest request){
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);

        List<Book> bookList = bookService.pageBookList(pageNo, pageSize);

        PageInfo pageInfo = new PageInfo(bookList);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("flag", "client");

        return "/client/index";
    }

    @RequestMapping("/pageByPrice")
    protected String pageByPrice(HttpServletRequest request){
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);

        List<Book> bookList = bookService.pageBookListByPrice(pageNo, pageSize, min, max);
        PageInfo pageInfo = new PageInfo(bookList);

        request.setAttribute("pageInfo",pageInfo);
        request.setAttribute("flag", "client");

        return "/client/index";
    }
}
