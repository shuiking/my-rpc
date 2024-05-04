package com.lk.rpccore.config;

import com.lk.rpccore.constant.*;
import lombok.Data;

/**
 * RPC 框架配置
 *
 * @Author : lk
 */

@Data
public class RpcConfig {

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 名称
     */
    private String name = "my-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 序列化器
     */
    private String serializer = SerializerConstant.JDK;

    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerConstant.ROUND_ROBIN;

    /**
     * 注册表配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
    /**
     * 重试策略
     */
    private String retryStrategy = RetryStrategyConstant.NO;
    /**
     * 容错策略
     */
    private String tolerantStrategy = TolerantStrategyConstant.FAIL_OVER;
    /**
     * 模拟服务
     */
    private String mockService = MockServiceConstant.DEFAULT;
}
