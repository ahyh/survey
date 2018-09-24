package com.yh.survey.domain.pojo;

import com.yh.survey.domain.BaseDomain;

import java.util.LinkedHashSet;

/**
 * Bag
 *
 * @author yanhuan
 */
public class Bag extends BaseDomain {

    /**
     * 包裹编号
     */
    private String bagNo;

    /**
     * 包裹名称
     */
    private String bagName;

    /**
     * 包裹序号
     */
    private Integer bagOrder;

    /**
     * 所属调查编号
     */
    private Long surveyId;

    /**
     * 包裹中问题集合
     */
    private LinkedHashSet<Question> questionSet;

    public String getBagNo() {
        return bagNo;
    }

    public void setBagNo(String bagNo) {
        this.bagNo = bagNo;
    }

    public String getBagName() {
        return bagName;
    }

    public void setBagName(String bagName) {
        this.bagName = bagName;
    }

    public Integer getBagOrder() {
        return bagOrder;
    }

    public void setBagOrder(Integer bagOrder) {
        this.bagOrder = bagOrder;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public LinkedHashSet<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(LinkedHashSet<Question> questionSet) {
        this.questionSet = questionSet;
    }
}
