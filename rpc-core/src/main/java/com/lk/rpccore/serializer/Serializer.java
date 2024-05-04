package com.lk.rpccore.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @Author : lk
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object 对象
     * @param <T> 泛型
     * @return byte[]
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes 字节数据
     * @param tClass 类的类型
     * @param <T> 泛型
     * @return T
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> tClass) throws IOException;
}
