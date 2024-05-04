package com.lk.rpccore.fault.retry;

import com.lk.rpccore.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 不重试 - 重试策略
 *
 * @Author : lk
 */

public class NoRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable 调用
     * @return RpcResponse
     * @throws Exception 异常
     */
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
