package com.yh.survey.exceptions;

/**
 * 上传文件太大异常
 *
 * @author yanhuan
 */
public class FileTooLargeException extends RuntimeException {

    public FileTooLargeException(String message) {
        super(message);
    }
}
