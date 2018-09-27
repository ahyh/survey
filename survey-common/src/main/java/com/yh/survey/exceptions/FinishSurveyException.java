package com.yh.survey.exceptions;

/**
 * 完成调查提交答案数据出现异常
 *
 * @author yanhuan
 */
public class FinishSurveyException extends RuntimeException {

    public FinishSurveyException(String message) {
        super(message);
    }
}
