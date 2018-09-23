package com.yh.survey.domain.pojo;

import com.yh.survey.domain.BaseDomain;

/**
 * Question
 *
 * @author yanhuan
 */
public class Question extends BaseDomain {

    private String questionNo;

    private String questionName;

    private Byte questionType;

    private String questionOptions;

    private String bagNo;

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
        this.questionOptions = questionOptions;
    }

    public String getBagNo() {
        return bagNo;
    }

    public void setBagNo(String bagNo) {
        this.bagNo = bagNo;
    }
}
