package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.AdminCondition;
import com.yh.survey.domain.manager.pojo.Admin;

/**
 * UserDao
 *
 * @author yanhuan
 */
public interface AdminDao {

    Integer insert(Admin admin);

    Integer update(Admin admin);

    Integer delete(Long id);

    Admin getAdminByCondition(AdminCondition condition);
}