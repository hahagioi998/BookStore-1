package com.levy.service.impl;

import com.levy.domain.User;
import com.levy.mapper.UserMapper;
import com.levy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LevyXie
 * @create 2022-01-11 17:39
 * @description
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public void registerUser(User user) {
        userMapper.saveUser(user);
    }

    public User userLogin(User user) {
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    public boolean existsUsername(String username) {
        if(userMapper.queryUserByUsername(username) == null) {
            return true;//未查询到,可用
        }else{
            return false;//已查询到,不可用
        }
    }
}
