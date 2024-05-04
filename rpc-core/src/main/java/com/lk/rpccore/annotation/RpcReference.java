package com.lk.rpccore.annotation;

import com.lk.rpccore.constant.LoadBalancerConstant;
import com.lk.rpccore.constant.RetryStrategyConstant;
import com.lk.rpccore.constant.RpcConstant;
import com.lk.rpccore.constant.TolerantStrategyConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务消费者注解（用于注入服务）
 *
 * @Author : lk
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RpcReference {
    /**
     * 服务接口类
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 负载均衡器
     */
    String loadBalancer() default LoadBalancerConstant.ROUND_ROBIN;

    /**
     * 重试策略
     */
    String retryStrategy() default RetryStrategyConstant.NO;

    /**
     * 容错策略
     */
    String tolerantStrategy() default TolerantStrategyConstant.FAIL_FAST;

    /**
     * 模拟调用
     */
    boolean mock() default false;
}
