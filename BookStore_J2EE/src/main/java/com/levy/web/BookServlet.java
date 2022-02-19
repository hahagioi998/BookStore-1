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
import java.util.List;

/**
 * @author LevyXie
 * @create 2022-01-14 17:12
 * @description:管理员对图书管理的Servlet
 */
public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        pageNo += 1;
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        bookService.addBook(book);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        //将字符串转化为int类型
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用Service层的删除方法
        bookService.deleteBookById(id);
        //重定向回list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");

        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        bookService.updateBook(book);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+ pageNo);
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    //-------------------------处理分页-------------------------------
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
