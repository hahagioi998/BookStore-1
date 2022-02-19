package com.levy.dao;

import com.levy.pojo.User;

/**
 * @author LevyXie
 * @create 2022-01-11 16:52
 * @description
 */
public interface UserDao{

    public User queryUserByUsername(String username);

    public User queryUserByUsernameAndPassword(String username, String password);

    public int saveUser(User user);

}
