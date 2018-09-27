package com.yh.survey.domain.guest.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * Survey表查询条件
 *
 * @author yanhuan
 */
public class AnswerCondition extends BasePageCondition {

    /**
     * 答案内容
     */
    private String answerContent;

    /**
     * 标识哪一次参与的调查
     */
    private String answerUuid;

    /**
     * 调查主键
     */
    private Long surveyId;

    /**
     * 问题主键
     */
    private Long questionId;

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerUuid() {
        return answerUuid;
    }

    public void setAnswerUuid(String answerUuid) {
        this.answerUuid = answerUuid;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
