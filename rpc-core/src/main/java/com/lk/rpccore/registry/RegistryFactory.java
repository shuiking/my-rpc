package com.lk.rpccore.registry;

import com.lk.rpccore.spi.SpiLoader;

/**
 * 注册中心工厂（用于获取注册中心对象）
 *
 * @Author : lk
 */

public class RegistryFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new ZooKeeperRegistry();

    /**
     * 获取实例
     *
     * @param key 键值
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
