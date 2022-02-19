package com.levy.web;

import com.levy.pojo.Book;
import com.levy.pojo.Page;
import com.levy.service.BookService;
import com.levy.service.impl.BookServiceImpl;
import com.levy.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LevyXie
 * @create 2022-01-15 16:45
 * @description
 */
public class ClientBookServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/clientBookServlet?action=page");

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    //根据价格查询书籍功能的简单实现
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if(req.getParameter("min") != null){
            sb.append("&min=" + req.getParameter("min"));
        }
        if(req.getParameter("max") != null){
            sb.append("&max=" + req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
