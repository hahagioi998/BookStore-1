package com.levy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.levy.domain.Book;
import com.levy.mapper.BookMapper;
import com.levy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LevyXie
 * @create 2022-01-14 17:02
 * @description
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    public void deleteBookById(Integer id) {
        bookMapper.deleteBookById(id);
    }

    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }

    public List<Book> queryBooks() {
        return bookMapper.queryBooks();
    }

    public List<Book> pageBookList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return bookMapper.queryBooks();
    }

    public List<Book> pageBookListByPrice(int pageNo, int pageSize, int min, int max) {

        PageHelper.startPage(pageNo, pageSize);
        return bookMapper.queryBooksByPrice(min,max);
    }
}
