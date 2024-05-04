package com.lk.rpccore.proxy;

import cn.hutool.core.collection.CollUtil;
import com.lk.rpccore.RpcCoreApplication;
import com.lk.rpccore.config.RpcConfig;
import com.lk.rpccore.constant.RpcConstant;
import com.lk.rpccore.exception.RpcException;
import com.lk.rpccore.fault.retry.RetryStrategy;
import com.lk.rpccore.fault.retry.RetryStrategyFactory;
import com.lk.rpccore.fault.tolerant.TolerantStrategy;
import com.lk.rpccore.fault.tolerant.TolerantStrategyFactory;
import com.lk.rpccore.loadbalancer.LoadBalancer;
import com.lk.rpccore.loadbalancer.LoadBalancerFactory;
import com.lk.rpccore.model.RpcRequest;
import com.lk.rpccore.model.RpcResponse;
import com.lk.rpccore.model.ServiceMetaInfo;
import com.lk.rpccore.registry.Registry;
import com.lk.rpccore.registry.RegistryFactory;
import com.lk.rpccore.server.tcp.VertxTcpClient;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务代理（JDK 动态代理）
 *
 * @Author : lk
 */

@Slf4j
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     * @param proxy  代理
     * @param method 方法
     * @param args   参数
     * @return Object
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcCoreApplication.getRpcConfig();
        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
        if (CollUtil.isEmpty(serviceMetaInfoList)) {
            throw new RpcException("暂无服务地址");
        }

        // 负载均衡
        LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
        // 将调用方法名（请求路径）作为负载均衡参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", rpcRequest.getMethodName());
        ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

        // 发送 rpc 请求
        // 使用重试机制
        RpcResponse rpcResponse;
        try {
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
            );
        } catch (Exception e) {
            // 容错机制
            HashMap<String, Object> map = new HashMap<>();
            map.put("serviceList", serviceMetaInfoList);
            //排查在外的服务
            map.put("errorService", selectedServiceMetaInfo);
            //传递rpcRequest
            map.put("rpcRequest", rpcRequest);
            TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
            rpcResponse = tolerantStrategy.doTolerant(map, e);
        }

        return rpcResponse.getData();

    }
}
