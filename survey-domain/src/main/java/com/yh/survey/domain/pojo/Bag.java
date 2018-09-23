package com.yh.survey.domain.pojo;

import com.yh.survey.domain.BaseDomain;

import java.util.LinkedHashSet;

/**
 * Bag
 *
 * @author yanhuan
 */
public class Bag extends BaseDomain {

    private String bagNo;

    private String bagName;

    private Integer bagOrder;

    private String surveyNo;

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

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public LinkedHashSet<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(LinkedHashSet<Question> questionSet) {
        this.questionSet = questionSet;
    }
}
