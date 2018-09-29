package com.yh.survey.dao;

import com.yh.survey.domain.guest.condition.UserCondition;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.pojo.Role;

import java.util.Set;

/**
 * UserDao
 *
 * @author yanhuan
 */
public interface UserDao {

    Integer insert(User user);

    Integer update(User salary);

    Integer delete(Long id);

    User getUserByCondition(UserCondition condition);

    Set<Role> getRoleSetDeeply(Long userId);
}
