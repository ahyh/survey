package com.yh.survey.domain.pojo;

import com.yh.survey.domain.BaseDomain;
import org.apache.commons.lang3.StringUtils;

/**
 * Question
 *
 * @author yanhuan
 */
public class Question extends BaseDomain {

    /**
     * 问题编号
     */
    private String questionNo;

    /**
     * 问题名称
     */
    private String questionName;

    /**
     * 问题类型：0-单选题，1-多选题，2-简答题
     */
    private Byte questionType;

    /**
     * 选项
     */
    private String questionOptions;

    /**
     * 问题所属包裹编号
     */
    private Long bagId;

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Byte getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Byte questionType) {
        this.questionType = questionType;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String questionOptions) {
        this.questionOptions = (StringUtils.isBlank(questionOptions)) ? "" : questionOptions.replaceAll("\r\n", ",");
    }

    public Long getBagId() {
        return bagId;
    }

    public void setBagId(Long bagId) {
        this.bagId = bagId;
    }

    /**
     * 展示问题选项
     *
     * @return
     */
    public String[] getOptionsArray() {
        return this.questionOptions.split(",");
    }

    /**
     * 编辑问题用于修改
     *
     * @return
     */
    public String getOptionsForEdit() {
        return this.questionOptions.replaceAll(",", "\r\n");
    }
}
