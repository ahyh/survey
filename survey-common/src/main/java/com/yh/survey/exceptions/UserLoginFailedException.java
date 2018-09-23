package com.yh.survey.exceptions;

/**
 * 用户登录失败异常
 */
public class UserLoginFailedException extends RuntimeException {

    public UserLoginFailedException(String message) {
        super(message);
    }
}
