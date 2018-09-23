package com.yh.survey.exceptions;

/**
 * 上传文件类型不正确异常
 *
 * @author yanhuan
 */
public class FileTypeInvalidException extends RuntimeException {

    public FileTypeInvalidException(String message) {
        super(message);
    }
}
