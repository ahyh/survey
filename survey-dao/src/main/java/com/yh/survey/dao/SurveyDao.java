package com.yh.survey.dao;

import com.yh.survey.domain.guest.condition.SurveyCondition;
import com.yh.survey.domain.guest.pojo.Survey;

import java.util.List;

public interface SurveyDao {

    Integer insert(Survey user);

    Integer update(Survey survey);

    Integer delete(Long id);

    Survey getSurveyByCondition(SurveyCondition condition);

    List<Survey> findSurveyList(SurveyCondition condition);

    /**
     * 根据Survey的id获取Survey对象，包含所有的包裹和问题
     *
     * @param id survey表的id
     * @return 包含包裹和问题的Survey对象
     */
    Survey getSurveyWithBagAndQuestions(Long id);

}
