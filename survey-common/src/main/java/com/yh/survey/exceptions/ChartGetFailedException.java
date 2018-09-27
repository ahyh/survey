package com.yh.survey.exceptions;

/**
 * 根据questionId获取Chart图表异常
 *
 * @author yanhuan
 */
public class ChartGetFailedException extends RuntimeException {

    public ChartGetFailedException(String message) {
        super(message);
    }
}
