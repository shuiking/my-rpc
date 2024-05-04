package com.lk.rpccore.fault.tolerant;

import com.lk.rpccore.spi.SpiLoader;

/**
 * 模拟服务工厂
 *
 * @Author : lk
 */

public class MockServiceFactory {

    static {
        SpiLoader.load(MockService.class);
    }


    /**
     * 获取实例
     *
     * @param key 键值
     * @return MockService
     */
    public static MockService getInstance(String key) {
        return SpiLoader.getInstance(MockService.class, key);
    }

}
