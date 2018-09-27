package com.yh.survey.exceptions;

/**
 * admin登录失败异常
 *
 * @author yanhuan
 */
public class AdminLoginFailedException extends RuntimeException {

    public AdminLoginFailedException(String message) {
        super(message);
    }
}
