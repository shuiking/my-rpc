package com.lk.rpccore.constant;

/**
 * 重试策略键名常量
 *
 * @Author : lk
 */

public interface RetryStrategyConstant {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";

    /**
     * 指数退避
     */
    String EXPONENTIAL_BACKOFF = "exponentialBackoff";

    /**
     * 时间延迟
     */
    String RANDOM_DELAY = "randomDelay";
}
