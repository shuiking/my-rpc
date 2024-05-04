package com.lk.rpccore.serializer;

import com.lk.rpccore.exception.RpcException;

import java.io.*;

/**
 * JDK 序列化器
 *
 * @Author : lk
 */

public class JdkSerializer implements Serializer {

    /**
     * 序列化
     *
     * @param object 对象
     * @param <T> 泛型
     * @return byte[]
     * @throws IOException
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param bytes 字节
     * @param type  类型
     * @param <T> 泛型
     * @return T
     * @throws IOException
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (T) objectInputStream.readObject();

        } catch (ClassNotFoundException e) {
            throw new RpcException(e);
        }
    }
}
