package com.lk.rpccore.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * 服务元信息（注册信息）
 *
 * @Author : lk
 */

@Data
public class ServiceMetaInfo {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = "1.0";


    /**
     * 服务分组（暂未实现）
     */
    private String serviceGroup = "default";
    private String serviceHost;
    private int servicePort;


    /**
     * 获取服务键名
     *
     * @return {@link String}
     */
    public String getServiceKey() {
        // 后续可扩展服务分组
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return String
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    /**
     * 获取完整服务地址
     *
     * @return String
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}
