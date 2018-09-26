package com.yh.survey.guest.interf;

import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;

/**
 * 包裹服务
 */
public interface BagService {

    /**
     * 根据包裹id获取包含Question集合的Bag对象
     *
     * @param bagId 包裹id
     * @return Bag对象，包含Question集合
     */
    Bag getBagWithQuestions(Long bagId);

    Bag getBagByCondition(BagCondition condition);

    Integer saveBag(Bag bag);

    /**
     * 删除包裹，如果包裹下没有问题则可以直接删除，否则提示失败
     *
     * @param condition 包裹id,updateUser信息
     * @return 删除包裹受影响的行数
     */
    Integer removeBag(BagCondition condition);

    Integer updateBag(Bag bag);

    /**
     * 根据surveyId查询该调查下包裹数量
     *
     * @param surveyId survey_id
     * @return 调查下包裹数量
     */
    Integer queryBagNumBySurveyId(Long surveyId);

    /**
     * 删除包裹及包裹下所有问题
     *
     * @param condition 包含bagId，updateUser信息
     * @return 是否成功删除
     */
    Boolean removeBagWithQuestions(BagCondition condition);
}
