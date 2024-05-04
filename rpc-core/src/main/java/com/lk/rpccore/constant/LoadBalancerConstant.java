package com.lk.rpccore.constant;

/**
 * * 负载均衡器键名常量
 *
 * @Author : lk
 */

public interface LoadBalancerConstant {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    /**
     * 随机
     */
    String RANDOM = "random";

    /**
     * 一致哈希
     */
    String CONSISTENT_HASH = "consistentHash";
}
