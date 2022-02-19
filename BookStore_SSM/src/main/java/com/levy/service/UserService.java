package com.levy.service;


import com.levy.domain.User;

/**
 * @author LevyXie
 * @create 2022-01-11 17:36
 * @description
 */
public interface UserService {

    public void registerUser(User user);

    public User userLogin(User user);

    public boolean existsUsername(String username);
}
