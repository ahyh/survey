package com.yh.survey.domain.guest.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * Survey表查询条件
 *
 * @author yanhuan
 */
public class SurveyCondition extends BasePageCondition {

    private String surveyNo;

    private String surveyName;

    private Byte surveyStatus;

    private String logoPath;

    private String username;

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public Byte getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(Byte surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
