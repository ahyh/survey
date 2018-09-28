package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Res;

/**
 * ResDao
 *
 * @author yanhuan
 */
public interface ResDao {

    Integer insert(Res res);

    Integer update(Res res);

    Integer delete(Long id);

    Res getResByCondition(ResCondition condition);

    Integer getMaxResPos();

    Integer getMaxResCode(Integer maxPos);
}
