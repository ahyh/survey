package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.AuthCondition;
import com.yh.survey.domain.manager.pojo.Auth;

/**
 * AuthDao
 *
 * @author yanhuan
 */
public interface AuthDao {

    Integer insert(Auth res);

    Integer update(Auth res);

    Integer delete(Auth id);

    Auth getAuthByCondition(AuthCondition condition);
}
