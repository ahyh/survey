package com.yh.survey.dao;

import com.yh.survey.domain.condition.SurveyCondition;
import com.yh.survey.domain.pojo.Survey;

import java.util.List;

public interface SurveyDao {

    Integer insert(Survey user);

    Integer update(Survey survey);

    Integer delete(Long id);

    Survey getSurveyByCondition(SurveyCondition condition);

    List<Survey> findSurveyList(SurveyCondition condition);

}
