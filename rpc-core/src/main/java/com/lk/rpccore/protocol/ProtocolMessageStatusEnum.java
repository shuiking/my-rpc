package com.lk.rpccore.protocol;

import lombok.Getter;

/**
 * 协议消息的状态枚举
 *
 * @Author : lk
 */

@Getter
public enum ProtocolMessageStatusEnum {

    /**
     * 成功
     */
    OK("ok", 20),
    BAD_REQUEST("badRequest", 40),
    BAD_RESPONSE("badResponse", 50);

    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 值
     * @return ProtocolMessageStatusEnum
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
