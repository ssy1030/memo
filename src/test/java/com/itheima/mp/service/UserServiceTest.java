package com.itheima.mp.service;

import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private  UserService userService;

    @Test
    public void toQueryUser(){
        User user = userService.getById(5L);
    }

    public static void main(String []args){
        System.out.println(List.of(1L,2L,3L).add(6L));
    }
}