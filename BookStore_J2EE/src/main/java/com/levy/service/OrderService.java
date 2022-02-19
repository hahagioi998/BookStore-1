package com.levy.service;

import com.levy.pojo.Cart;

/**
 * @author LevyXie
 * @create 2022-01-19 22:17
 * @description
 */
public interface OrderService {

    public String createOrder(Cart cart, Integer userId);

}
