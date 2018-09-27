package com.yh.survey.dao;

import com.yh.survey.domain.guest.condition.BagCondition;
import com.yh.survey.domain.guest.pojo.Bag;

import java.util.List;

public interface BagDao {

    Integer insert(Bag bag);

    Integer update(Bag bag);

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

    /**
     * 根据surveyId查询Bag集合
     *
     * @param surveyId surveyId
     * @return Bag集合
     */
    List<Bag> findBagListBySurveyId(Long surveyId);
}
