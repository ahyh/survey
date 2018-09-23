package com.yh.survey.dao;

import com.yh.survey.domain.condition.UserCondition;
import com.yh.survey.domain.pojo.User;

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
}
