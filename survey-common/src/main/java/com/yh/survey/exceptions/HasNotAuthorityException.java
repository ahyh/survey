package com.yh.survey.exceptions;

/**
 * 没有操作权限异常
 *
 * @author yanhuan
 */
public class HasNotAuthorityException extends RuntimeException {

    public HasNotAuthorityException(String message) {
        super(message);
    }
}
