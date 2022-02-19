package com.levy.dao.impl;

import com.levy.dao.BaseDAO;
import com.levy.dao.OrderItemDao;
import com.levy.pojo.OrderItem;

/**
 * @author LevyXie
 * @create 2022-01-19 21:57
 * @description
 */
public class OrderItemDaoImpl extends BaseDAO implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "Insert into t_order_item (`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";

        return update(sql, item.getName(), item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
    }
}
