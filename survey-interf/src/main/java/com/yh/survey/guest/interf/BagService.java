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

    Integer removeBag(Long bagId);

    Integer updateBag(Bag bag);
}
