package com.yh.survey.domain.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * Survey表查询条件
 *
 * @author yanhuan
 */
public class QuestionCondition extends BasePageCondition {

    private String questionNo;

    private String questionName;

    private Byte questionType;

    private String questionOptions;

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
        this.questionOptions = questionOptions;
    }

    public Long getBagId() {
        return bagId;
    }

    public void setBagId(Long bagId) {
        this.bagId = bagId;
    }
}
