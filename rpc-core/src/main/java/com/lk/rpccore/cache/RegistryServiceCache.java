package com.lk.rpccore.cache;


import com.lk.rpccore.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册服务缓存
 *
 * @Author : lk
 */

public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     * @param newServiceCache 新建服务缓存
     */
    public void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     * @return List<ServiceMetaInfo>
     */
    public List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        this.serviceCache = null;
    }
}
