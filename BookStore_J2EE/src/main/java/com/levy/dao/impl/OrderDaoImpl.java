package com.levy.dao.impl;

import com.levy.dao.BaseDAO;
import com.levy.dao.OrderDao;
import com.levy.pojo.Order;

/**
 * @author LevyXie
 * @create 2022-01-19 21:47
 * @description
 */
public class OrderDaoImpl extends BaseDAO implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "Insert into t_order values(?,?,?,?,?)";

        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
