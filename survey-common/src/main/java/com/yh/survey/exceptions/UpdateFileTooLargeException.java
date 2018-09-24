package com.yh.survey.exceptions;

/**
 * 上传文件太大异常
 *
 * @author yanhuan
 */
public class UpdateFileTooLargeException extends RuntimeException {

    public UpdateFileTooLargeException(String message) {
        super(message);
    }
}
