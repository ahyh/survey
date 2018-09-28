package com.yh.survey.exceptions;

/**
 * admin登录失败异常
 *
 * @author yanhuan
 */
public class BatchDeleteFailedException extends RuntimeException {

    public BatchDeleteFailedException(String message) {
        super(message);
    }
}
