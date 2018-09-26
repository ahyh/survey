package com.yh.survey.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型枚举
 *
 * @author yanhuan
 */
public enum SurveyStatusEnum {

    /**
     * 未完成
     */
    UNCOMPLETE((byte) 0,"未完成"),

    /**
     * 完成
     */
    COMPLETED((byte) 1,"完成");

    public static final Map<Byte, SurveyStatusEnum> surveyStatusEnumMap = new HashMap<Byte, SurveyStatusEnum>();

    static {
        for (SurveyStatusEnum e : EnumSet.allOf(SurveyStatusEnum.class)) {
            surveyStatusEnumMap.put(e.getKey(), e);
        }
    }

    private Byte key;

    private String value;

    SurveyStatusEnum(Byte key, String value) {
        this.key = key;
        this.value = value;
    }

    public Byte getKey() {
        return key;
    }

    public void setKey(Byte key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
