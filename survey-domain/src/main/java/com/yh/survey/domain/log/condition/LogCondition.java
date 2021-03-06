package com.yh.survey.domain.log.condition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LogCondition implements Serializable {

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
    private String operatorTime;

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

    private int pageNum;

    private int pageSize;

    private String orderBy;

    List<String> tableNames;

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

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    @Override
    public String toString() {
        return "LogCondition{" +
                "id=" + id +
                ", operator='" + operator + '\'' +
                ", operatorTime='" + operatorTime + '\'' +
                ", methodName='" + methodName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", inputData='" + inputData + '\'' +
                ", outputData='" + outputData + '\'' +
                ", exceptionType='" + exceptionType + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", tableNames=" + tableNames +
                '}';
    }
}
