package com.example.shiro;

import com.example.shiro.model.entity.User;
import com.example.shiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        User user = userService.findByUserName("u5");
        System.out.println(userService.username("u2"));
    }

}
