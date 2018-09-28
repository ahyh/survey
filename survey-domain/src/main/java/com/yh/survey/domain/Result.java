package com.yh.survey.domain;

public class Result {

    private Integer resultCode;

    private Object resultValue;

    private String errorMsg;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultValue() {
        return resultValue;
    }

    public void setResultValue(Object resultValue) {
        this.resultValue = resultValue;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
