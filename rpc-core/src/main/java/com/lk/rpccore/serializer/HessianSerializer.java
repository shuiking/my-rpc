package com.lk.rpccore.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.lk.rpccore.exception.RpcException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Hessian 序列化器
 *
 * @Author : lk
 */
public class HessianSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);
            output.writeObject(t);
            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();
            data = os.toByteArray();
        } catch (Exception e) {
            throw new RpcException(e);
        }
        return data;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null) {
            return null;
        }
        Object result = null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            Hessian2Input input = new Hessian2Input(is);
            result = input.readObject();
        } catch (Exception e) {
            throw new RpcException(e);
        }
        return (T) result;
    }
}
