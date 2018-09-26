package com.yh.survey.dao;

import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;

public interface BagDao {

    Integer insert(Bag user);

    Integer update(Bag salary);

    Integer delete(BagCondition condition);

    Bag getBagByCondition(BagCondition condition);

    /**
     * 根据bagId获取Bag对象，包含bag下的所有Question
     *
     * @param bagId 包裹ID
     * @return Bag对象，包含Question
     */
    Bag getBagWithQuestions(Long bagId);

    /**
     * 根据surveyId查询该调查下包裹数量
     *
     * @param surveyId survey_id
     * @return 调查下包裹数量
     */
    Integer queryBagNumBySurveyId(Long surveyId);
}
