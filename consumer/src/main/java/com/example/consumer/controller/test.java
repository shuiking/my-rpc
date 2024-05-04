package com.example.consumer.controller;

import com.example.examplecommon.model.User;
import com.example.examplecommon.service.UserService;
import com.lk.rpccore.annotation.RpcReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lk
 */
@RestController
@RequestMapping("/test")
public class test {
    @RpcReference
    private UserService userService;
    @GetMapping("/")
    public void get(){
        User user = new User();
        user.setName("hhh");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
