package com.yh.survey.exceptions;

/**
 * 上传文件类型不正确异常
 *
 * @author yanhuan
 */
public class ExportExcelFailedException extends RuntimeException {

    public ExportExcelFailedException(String message) {
        super(message);
    }
}
