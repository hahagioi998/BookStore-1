package com.levy.service.impl;

import com.levy.dao.BookDao;
import com.levy.dao.OrderDao;
import com.levy.dao.OrderItemDao;
import com.levy.dao.impl.*;
import com.levy.pojo.*;
import com.levy.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LevyXie
 * @create 2022-01-19 22:18
 * @description
 */
public class OrderServiceImpl implements OrderService {

    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    public String createOrder(Cart cart, Integer userId) {
        String orderId  = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),new BigDecimal(cart.getTotalPrice()),0,userId);
        orderDao.saveOrder(order);


        for(CartItem item : cart.getItems().values()){
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getCount(), new BigDecimal(item.getPrice()), new BigDecimal(item.getTotalPrice()), orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales() + item.getCount());
            book.setStock(book.getStock() - item.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
