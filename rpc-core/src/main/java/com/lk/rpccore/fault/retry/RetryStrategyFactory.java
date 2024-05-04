package com.lk.rpccore.fault.retry;

import com.lk.rpccore.spi.SpiLoader;

/**
 * 重试策略工厂（用于获取重试器对象）
 *
 * @Author : lk
 */

public class RetryStrategyFactory {

    private RetryStrategyFactory() {

    }

    static {
        SpiLoader.load(RetryStrategy.class);
    }

    /**
     * 默认重试器
     */
    private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();


    /**
     * 获取实例
     *
     * @param key 键值
     * @return RetryStrategy
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }

}
