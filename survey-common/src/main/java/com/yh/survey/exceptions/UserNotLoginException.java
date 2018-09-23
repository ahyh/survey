package com.yh.survey.exceptions;

/**
 * 用户未登录异常
 *
 * @author yanhuan
 */
public class UserNotLoginException extends RuntimeException {

    public UserNotLoginException(String message) {
        super(message);
    }
}
