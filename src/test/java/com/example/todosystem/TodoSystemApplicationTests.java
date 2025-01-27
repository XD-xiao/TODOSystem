package com.example.todosystem;

import com.example.todosystem.pojo.User;
import com.example.todosystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TodoSystemApplicationTests {

    @Autowired
    private UserService userService;


    @Test
    public void test(){

        User user = new User();

        user.setEmail("user222@example.com");
        user.setPassword("P@ssw0rd!");

        User resUser =userService.login(user);

        if (resUser != null ) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

    }


}
