package com.lk.rpccore.fault.retry;

import com.github.rholder.retry.*;
import com.lk.rpccore.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 指数退避 - 重试策略
 *
 * @Author : lk
 */

@Slf4j
public class ExponentialBackoffRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable 调用
     * @return RpcResponse
     * @throws ExecutionException 执行异常
     * @throws RetryException     重试异常
     */
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfExceptionOfType(Exception.class)
                .withWaitStrategy(WaitStrategies.exponentialWait(1000, 30L, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}, 距离第一次重试的延迟 {} 毫秒", attempt.getAttemptNumber() - 1, attempt.getAttemptNumber() - 1 == 0 ? 0 : attempt.getDelaySinceFirstAttempt());
                    }
                })
                .build();

        return retryer.call(callable);
    }
}
