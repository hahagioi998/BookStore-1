package com.levy.service.impl;

import com.levy.dao.UserDao;
import com.levy.dao.impl.UserDaoImpl;
import com.levy.pojo.User;
import com.levy.service.UserService;

/**
 * @author LevyXie
 * @create 2022-01-11 17:39
 * @description
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    public User userLogin(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) == null) {
            return true;//未查询到,可用
        }else{
            return false;//已查询到,不可用
        }
    }
}
