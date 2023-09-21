package com.demo.service;

import com.demo.Application;
import com.demo.pojo.User;
import com.demo.utils.RandomNameUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void registerTest(){

        for (int i = 0; i < 20; i++) {

            User user = new User();
            int gender = (int)(Math.random()*2)+1;
            user.setUsername(RandomNameUtil.getUsername(gender));
            user.setGender(gender);
            user.setPassword("123456");
            Random random = new Random();
            user.setAge(random.nextInt(25)+15);

            boolean register = userService.register(user);

        }

    }

}
