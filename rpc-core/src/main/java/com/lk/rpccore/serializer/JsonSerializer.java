package com.lk.rpccore.serializer;

import com.alibaba.fastjson.JSON;

/**
 * Json 序列化器
 *
 * @Author : lk
 */

public class JsonSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T t) {
        String jsonStr = JSON.toJSONString(t);
        return jsonStr.getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }

}
