package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.RoleCondition;
import com.yh.survey.domain.manager.pojo.Role;

/**
 * AuthDao
 *
 * @author yanhuan
 */
public interface RoleDao {

    Integer insert(Role res);

    Integer update(Role res);

    Integer delete(Role id);

    Role getRoleByCondition(RoleCondition condition);
}
