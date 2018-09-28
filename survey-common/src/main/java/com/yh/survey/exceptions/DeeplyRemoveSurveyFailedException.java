package com.yh.survey.exceptions;

/**
 * 深度删除调查失败异常
 *
 * @author yanhuan
 */
public class DeeplyRemoveSurveyFailedException extends RuntimeException {

    public DeeplyRemoveSurveyFailedException(String message) {
        super(message);
    }
}
