package com.lk.rpccore.config;

import com.lk.rpccore.constant.RegistryConstant;
import lombok.Data;


/**
 * RPC 框架注册中心配置
 *
 * @Author : lk
 */

@Data
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = RegistryConstant.ZOOKEEPER;

    /**
     * 注册中心地址
     */
    private String address = "localhost:2181";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;
}
