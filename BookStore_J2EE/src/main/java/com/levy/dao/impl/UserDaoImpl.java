package com.levy.dao.impl;

import com.levy.dao.BaseDAO;
import com.levy.dao.UserDao;
import com.levy.pojo.User;

/**
 * @author LevyXie
 * @create 2022-01-11 16:56
 * @description
 */
public class UserDaoImpl extends BaseDAO implements UserDao {

    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
