package com.lk.rpccore.fault.tolerant;

import com.lk.rpccore.spi.SpiLoader;

/**
 * 容错策略工厂（工厂模式，用于获取容错策略对象）
 *
 * @Author : lk
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_RETRY_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key 键值
     * @return TolerantStrategy
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
