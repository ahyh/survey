package com.yh.survey.dao;

import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;

public interface BagDao {

    Integer insert(Bag user);

    Integer update(Bag salary);

    Integer delete(Long id);

    Bag getBagByCondition(BagCondition condition);
}
