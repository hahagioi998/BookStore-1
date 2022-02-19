package com.levy.service;

import com.levy.domain.Book;

import java.util.List;

/**
 * @author LevyXie
 * @create 2022-01-14 17:00
 * @description
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public List<Book> pageBookList(int pageNo, int pageSize);

    public List<Book> pageBookListByPrice(int pageNo, int pageSize, int min, int max);

}
