package com.yh.survey.exceptions;

/**
 * 用户名已存在异常
 *
 * @author yanhuan
 */
public class UserNameAlreadyExistsException extends RuntimeException {

    public UserNameAlreadyExistsException(String message) {
        super(message);
    }
}
