package com.levy.service.impl;

import com.levy.domain.*;
import com.levy.mapper.BookMapper;
import com.levy.mapper.OrderItemMapper;
import com.levy.mapper.OrderMapper;
import com.levy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LevyXie
 * @create 2022-01-19 22:18
 * @description
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookMapper bookMapper;

    public String createOrder(Cart cart, Integer userId) {
        String orderId  = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),new BigDecimal(cart.getTotalPrice()),0,userId);
        orderMapper.saveOrder(order);


        for(CartItem item : cart.getItems().values()){
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getCount(), new BigDecimal(item.getPrice()), new BigDecimal(item.getTotalPrice()), orderId);
            orderItemMapper.saveOrderItem(orderItem);
            Book book = bookMapper.queryBookById(item.getId());
            book.setSales(book.getSales() + item.getCount());
            book.setStock(book.getStock() - item.getCount());
            bookMapper.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
