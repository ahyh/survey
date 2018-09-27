package com.yh.survey.domain.guest.condition;

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

    private Long surveyId;

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
}
