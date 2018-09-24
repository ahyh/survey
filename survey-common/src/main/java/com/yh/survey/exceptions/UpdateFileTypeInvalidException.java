package com.yh.survey.exceptions;

/**
 * 上传文件类型不正确异常
 *
 * @author yanhuan
 */
public class UpdateFileTypeInvalidException extends RuntimeException {

    public UpdateFileTypeInvalidException(String message) {
        super(message);
    }
}
