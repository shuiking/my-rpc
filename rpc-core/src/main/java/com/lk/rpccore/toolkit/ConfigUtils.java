package com.lk.rpccore.toolkit;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 *
 * @Author : lk
 */

public class ConfigUtils {

    /**
     * 加载配置对象
     * @param tClass 类的类型
     * @param prefix 前缀
     * @param <T> 泛型
     * @return T
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置
     * @param tClass 类的类型
     * @param prefix 前缀
     * @param environment 环境
     * @param <T> 泛型
     * @return T
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (CharSequenceUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}
