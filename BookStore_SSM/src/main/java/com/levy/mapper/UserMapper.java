package com.levy.mapper;

import com.levy.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description：
 * @author：LevyXie
 * @create：2022-02-18 9:39
 */
public interface UserMapper {

    public User queryUserByUsername(String username);

    public User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    public int saveUser(User user);

}
