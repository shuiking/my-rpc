package com.lk.rpccore;


import com.lk.rpccore.config.RegistryConfig;
import com.lk.rpccore.config.RpcConfig;
import com.lk.rpccore.constant.RpcConstant;
import com.lk.rpccore.exception.RpcException;
import com.lk.rpccore.registry.Registry;
import com.lk.rpccore.registry.RegistryFactory;
import com.lk.rpccore.toolkit.ConfigUtils;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RpcCoreApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig 新 RPC 配置
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());

        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册 Shutdown Hook，JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));

    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            log.info("配置加载失败，使用默认值");
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取 RPC 配置
     *
     * @return RPC 配置
     */
    public static RpcConfig getRpcConfig() {
        // 双重校验锁
        if (rpcConfig == null) {
            synchronized (RpcCoreApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
