package com.lk.rpccore.loadbalancer;

import com.lk.rpccore.spi.SpiLoader;

/**
 * 负载均衡器工厂（工厂模式，用于获取负载均衡器对象）
 *
 * @Author : lk
 */

public class LoadBalancerFactory {

    private LoadBalancerFactory() {
    }

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 获取实例
     *
     * @param key 键值
     * @return LoadBalancer
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }

}
