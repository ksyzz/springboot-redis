package com.example.shiro.service;

import com.example.shiro.model.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @author fengqian
 * @since <pre>2018/07/03</pre>
 */
@Service
public class UserService {

    @Cacheable(value = "user", key = "#userName")
    public User findByUserName(String userName) {
        System.out.println("执行方法！");
        User user = new User();
        user.setLocked(true);
        user.setName("admin");
        user.setUsername(userName);
        user.setPassword("123456");
        user.setLocked(false);
        return user;
    }

    @Cacheable(value = "user", key = "#userName")
    public String username(String userName) {
        System.out.println("执行");
        return userName;
    }
}
