package com.lk.rpccore.fault.retry;

import com.lk.rpccore.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 重试策略
 *
 * @Author : lk
 */

public interface RetryStrategy {

    /**
     * 重试
     *
     * @param callable 调用
     * @return RpcResponse
     * @throws Exception 异常
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
