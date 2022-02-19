package com.levy.mapper;

import com.levy.domain.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 9:39
 */
public interface BookMapper {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public List<Book> queryBooksByPrice(@Param("min") int min, @Param("max") int max);

}
