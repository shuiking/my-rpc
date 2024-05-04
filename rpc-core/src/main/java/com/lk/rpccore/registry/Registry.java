package com.lk.rpccore.registry;

import com.lk.rpccore.config.RegistryConfig;
import com.lk.rpccore.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心
 *
 * @Author : lk
 */

public interface Registry {

    /**
     * 初始化
     *
     * @param registryConfig 注册表配置
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo 服务信息
     * @throws Exception 例外
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo 服务信息
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     *
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();

    /**
     * 心跳检测（服务端）
     */
    void heartBeat();

    /**
     * 监听（消费端）
     *
     * @param serviceNodeKey 服务节点的键值
     */
    void watch(String serviceNodeKey);
}
