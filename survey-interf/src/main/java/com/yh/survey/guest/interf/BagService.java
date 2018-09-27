package com.yh.survey.guest.interf;

import com.yh.survey.domain.guest.condition.BagCondition;
import com.yh.survey.domain.guest.pojo.Bag;

import java.util.List;

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

    /**
     * 根据surveyId查询Bag集合
     *
     * @param surveyId surveyId
     * @return Bag集合
     */
    List<Bag> findBagListBySurveyId(Long surveyId);

    /**
     * 调整包裹顺序
     *
     * @param bagList 包含包裹的id，bagOrder,updateUser信息
     * @return 返回受影响的行数
     */
    Integer doAdjust(List<Bag> bagList);

    /**
     * 复制bagId对应的包裹到surveyId对应的调查下
     *
     * @param bagId      bag表主键
     * @param surveyId   survey表主键
     * @param updateUser 更新人
     */
    Integer updateRelationshipForCopy(Long bagId, Long surveyId, String updateUser);

    /**
     * 移动bagId对应的包裹到surveyId对应的调查下
     *
     * @param bagId      bag表主键
     * @param surveyId   survey表主键
     * @param updateUser 更新人
     */
    Integer updateRelationshipForMove(Long bagId, Long surveyId, String updateUser);
}
