package com.lk.rpccore.fault.tolerant;

import cn.hutool.core.collection.CollUtil;
import com.lk.rpccore.constant.LoadBalancerConstant;
import com.lk.rpccore.loadbalancer.LoadBalancer;
import com.lk.rpccore.loadbalancer.LoadBalancerFactory;
import com.lk.rpccore.model.RpcRequest;
import com.lk.rpccore.model.RpcResponse;
import com.lk.rpccore.model.ServiceMetaInfo;
import com.lk.rpccore.server.tcp.VertxTcpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 转移到其他服务节点 - 容错策略
 *
 * @Author : lk
 */

public class FailOverTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) throws ExecutionException, InterruptedException {
        //可自行扩展，获取其他服务节点并调用
        List<ServiceMetaInfo> serviceMetaInfoList = (List<ServiceMetaInfo>) context.get("serviceList");
        ServiceMetaInfo errorService = (ServiceMetaInfo) context.get("errorService");
        RpcRequest rpcRequest = (RpcRequest) context.get("rpcRequest");

        // 从服务列表中移除错误服务
        serviceMetaInfoList.remove(errorService);

        // 重新调用其他服务
        if (CollUtil.isNotEmpty(serviceMetaInfoList)) {
            // 重新调用其他服务
            // 负载均衡
            // 将调用方法名（请求路径）作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());

            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(LoadBalancerConstant.ROUND_ROBIN);
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            return VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
        }
        return null;
    }
}
