package com.yh.survey.guest.interf;

import com.github.pagehelper.PageInfo;
import com.yh.survey.domain.condition.SurveyCondition;
import com.yh.survey.domain.pojo.Survey;

public interface SurveyService {

    Integer insert(Survey survey);

    PageInfo<Survey> findSurveyPage(SurveyCondition condition);

    Integer removeSurvey(Long id);

    Survey getSurveyByCondition(SurveyCondition condition);

    Integer updateSurvey(Survey survey);
}
