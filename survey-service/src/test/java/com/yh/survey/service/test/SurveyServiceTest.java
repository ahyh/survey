package com.yh.survey.service.test;

import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.guest.interf.SurveyService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class SurveyServiceTest extends BaseTest {

    @Resource
    private SurveyService surveyService;

    @Test
    public void testGetSurveyWithBagAndQuestions() {
        Long id = 1L;
        Survey surveyWithBagAndQuestions = surveyService.getSurveyWithBagAndQuestions(id);
        Assert.assertTrue(surveyWithBagAndQuestions != null);
    }
}
