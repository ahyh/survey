package com.yh.survey.exceptions;

/**
 * 更新调查至完成状态异常
 *
 * @author yanhuan
 */
public class CompleteSurveyException extends RuntimeException {

    public CompleteSurveyException(String message) {
        super(message);
    }
}
