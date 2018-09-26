package com.yh.survey.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题类型枚举
 *
 * @author yanhuan
 */
public enum QuestionTypeEnum {

    SINGLE_CHOICE((byte) 0, "单选题"),

    MULTI_CHOICE((byte) 1, "多选题"),

    SHORT_ANSWER((byte) 2, "简答题");

    public static final Map<Byte, QuestionTypeEnum> questionTypeEnumMap = new HashMap<Byte, QuestionTypeEnum>();

    static {
        for (QuestionTypeEnum e : EnumSet.allOf(QuestionTypeEnum.class)) {
            questionTypeEnumMap.put(e.getKey(), e);
        }
    }

    private Byte key;

    private String value;

    private QuestionTypeEnum(Byte key, String value) {
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
