package com.yh.survey.domain.guest.pojo;

import com.yh.survey.domain.BaseDomain;

import java.util.LinkedHashSet;

/**
 * Survey对象
 *
 * @author yanhuan
 */
public class Survey extends BaseDomain {

    private String surveyNo;

    private String surveyName;

    private Byte surveyStatus;

    private String logoPath;

    private String username;

    private LinkedHashSet<Bag> bagSet;

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

    public LinkedHashSet<Bag> getBagSet() {
        return bagSet;
    }

    public void setBagSet(LinkedHashSet<Bag> bagSet) {
        this.bagSet = bagSet;
    }
}
