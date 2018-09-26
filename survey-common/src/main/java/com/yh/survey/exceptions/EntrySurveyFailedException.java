package com.yh.survey.exceptions;

/**
 * 上传文件类型不正确异常
 *
 * @author yanhuan
 */
public class EntrySurveyFailedException extends RuntimeException {

    public EntrySurveyFailedException(String message) {
        super(message);
    }
}
