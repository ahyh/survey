package com.yh.survey.exceptions;

/**
 * 用户注册失败异常
 *
 * @author yanhuan
 */
public class RegisterFailedException extends RuntimeException {

    public RegisterFailedException(String message) {
        super(message);
    }
}
