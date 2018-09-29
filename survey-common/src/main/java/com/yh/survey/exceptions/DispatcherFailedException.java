package com.yh.survey.exceptions;

/**
 * 分配权限失败异常
 *
 * @author yanhuan
 */
public class DispatcherFailedException extends RuntimeException {

    public DispatcherFailedException(String message) {
        super(message);
    }
}
