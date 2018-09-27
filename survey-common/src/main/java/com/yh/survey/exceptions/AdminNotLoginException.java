package com.yh.survey.exceptions;

/**
 * 用户未登录异常
 *
 * @author yanhuan
 */
public class AdminNotLoginException extends RuntimeException {

    public AdminNotLoginException(String message) {
        super(message);
    }
}
