package com.lk.rpccore.fault.tolerant;

import com.lk.rpccore.model.RpcResponse;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 容错策略
 *
 * @Author : lk
 */

public interface TolerantStrategy {

    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return RpcResponse
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e) throws ExecutionException, InterruptedException;
}
