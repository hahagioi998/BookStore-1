package com.levy.dao;

import com.levy.pojo.Book;

import java.util.List;

/**
 * @author LevyXie
 * @create 2022-01-14 12:39
 * @description
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForTotalPageCount();

    public List<Book> queryForPageItems(int begin, int pageSize);

    public Integer queryForTotalPageCountByPrice(int min, int max);

    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
