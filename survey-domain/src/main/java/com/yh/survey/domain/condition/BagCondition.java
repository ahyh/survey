package com.yh.survey.domain.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * Survey表查询条件
 *
 * @author yanhuan
 */
public class BagCondition extends BasePageCondition {

    private String bagNo;

    private String bagName;

    private Integer bagOrder;

    private String surveyNo;

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
}
