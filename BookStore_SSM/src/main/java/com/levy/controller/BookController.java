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
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 11:24
 */
@Controller
@RequestMapping("/superManager")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("add")
    protected String add(Book book, HttpServletRequest request){
        bookService.addBook(book);
        String pageNo = (String) request.getAttribute("pageNo");
        pageNo += 1;
        request.setAttribute("pageNo",pageNo);
        return "redirect:/superManager/page";
    }

    @RequestMapping("delete")
    protected String delete(int id){
        bookService.deleteBookById(id);
        return "redirect:/superManager/page";
    }

    @RequestMapping("update")
    protected String update(Book book){
        bookService.updateBook(book);
        return "redirect:/superManager/page";
    }

    @RequestMapping("getBookById")
    protected ModelAndView getBookById(int id){
        Book book = bookService.queryBookById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("book_edit");
        return modelAndView;
    }

    @RequestMapping("list")
    protected String list(){
        List<Book> books = bookService.queryBooks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", books);
        return "redirect:/superManager/page";
    }

    @RequestMapping("page")
    protected String page(HttpServletRequest req){
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 4);

        List<Book> bookList = bookService.pageBookList(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(bookList);
        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("flag", "superManager");

        return "manager/book_manager";
    }

}
