package com.example.consumer.service;

import com.example.examplecommon.model.User;
import com.example.examplecommon.service.UserService;
import com.lk.rpccore.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * @Author : lk
 */
@Service
public class ExampleServiceImpl {

    /**
     * 使用 Rpc 框架注入
     */
    @RpcReference
    private UserService userService;

    /**
     * 测试方法
     */
    public void test() {
        User user = new User();
        user.setName("hhh");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}