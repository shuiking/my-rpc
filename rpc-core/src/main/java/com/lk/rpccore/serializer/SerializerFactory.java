package com.lk.rpccore.serializer;

import com.lk.rpccore.spi.SpiLoader;

/**
 * 序列化器工厂（工厂模式，用于获取序列化器对象）
 *
 * @Author : lk
 */

public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key 键值
     * @return Serializer
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
