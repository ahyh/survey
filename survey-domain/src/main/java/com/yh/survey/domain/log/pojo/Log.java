package com.yh.survey.domain.log.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体类
 *
 * @author yanhuan
 */
public class Log implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operatorTime;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法所在类名
     */
    private String typeName;

    /**
     * 入参
     */
    private String inputData;

    /**
     * 出参
     */
    private String outputData;

    /**
     * 异常类型
     */
    private String exceptionType;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
