package com.example.provider.service;

import com.example.examplecommon.model.User;
import com.example.examplecommon.service.UserService;
import com.lk.rpccore.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * @Author : lk
 */

@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
