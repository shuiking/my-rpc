package com.lk.rpccore.fault.tolerant;

import com.lk.rpccore.RpcCoreApplication;
import com.lk.rpccore.config.RpcConfig;
import com.lk.rpccore.model.RpcResponse;

import java.util.Map;

/**
 * 降级到其他服务 - 容错策略
 *
 * @Author : lk
 */

public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // 可自行扩展，获取降级的服务并调用
        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcCoreApplication.getRpcConfig();
        MockService mockService = MockServiceFactory.getInstance(rpcConfig.getMockService());
        Object mock = mockService.mock();
        return RpcResponse.builder().data(mock).message("ok").build();
    }
}
