package com.levy.service.impl;

import com.levy.dao.BookDao;
import com.levy.dao.impl.BookDaoImpl;
import com.levy.pojo.Book;
import com.levy.pojo.Page;
import com.levy.service.BookService;

import java.util.List;

/**
 * @author LevyXie
 * @create 2022-01-14 17:02
 * @description
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);

    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //以下为分页的代码实现，目前可以采用PageHelper简化代码
        Integer pageTotalCount = bookDao.queryForTotalPageCount();
        Integer pageTotal = (pageTotalCount % pageSize == 0)?(pageTotalCount/pageSize):(pageTotalCount/pageSize + 1);

        Page<Book> bookPage = new Page<>();

        bookPage.setPageTotal(pageTotal);
        bookPage.setPageNo(pageNo);
        bookPage.setPageSize(pageSize);
        bookPage.setPageTotalCount(pageTotalCount);

        int begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        bookPage.setItems(items);
        return bookPage;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Integer pageTotalCount = bookDao.queryForTotalPageCountByPrice(min,max);
        Integer pageTotal = (pageTotalCount % pageSize == 0)?(pageTotalCount/pageSize):(pageTotalCount/pageSize + 1);

        Page<Book> bookPage = new Page<>();

        bookPage.setPageTotal(pageTotal);
        bookPage.setPageNo(pageNo);
        bookPage.setPageSize(pageSize);
        bookPage.setPageTotalCount(pageTotalCount);

        int begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        bookPage.setItems(items);
        return bookPage;
    }
}
