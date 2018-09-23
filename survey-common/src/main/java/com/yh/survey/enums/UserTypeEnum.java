package com.yh.survey.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型枚举
 *
 * @author yanhuan
 */
public enum UserTypeEnum {

    /**
     * 企业用户
     */
    COMPANY((byte) 1),

    /**
     * 个人用户
     */
    PERSONAL((byte) 0);

    public static final Map<Byte, UserTypeEnum> userTypeEnumMap = new HashMap<Byte, UserTypeEnum>();

    static {
        for (UserTypeEnum e : EnumSet.allOf(UserTypeEnum.class)) {
            userTypeEnumMap.put(e.getKey(), e);
        }
    }

    private Byte key;

    private UserTypeEnum(Byte key) {
        this.key = key;
    }

    public Byte getKey() {
        return key;
    }

    public void setKey(Byte key) {
        this.key = key;
    }
}
