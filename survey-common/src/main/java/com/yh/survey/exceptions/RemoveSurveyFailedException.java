package com.yh.survey.exceptions;

/**
 * 删除包裹失败异常，当包裹下面有问题未删除的时候删除包裹抛出此异常
 */
public class RemoveSurveyFailedException extends RuntimeException {

    public RemoveSurveyFailedException(String message) {
        super(message);
    }
}
