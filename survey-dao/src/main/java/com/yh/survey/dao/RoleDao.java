package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.RoleCondition;
import com.yh.survey.domain.manager.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * RoleDao
 *
 * @author yanhuan
 */
public interface RoleDao {

    Integer insert(Role role);

    Integer update(Role role);

    Integer delete(Long id);

    Role getRoleByCondition(RoleCondition condition);

    List<Role> findRoleList();

    Integer batchDelete(@Param("list") List<Long> roleIdList, @Param("updateUser") String updateUser);
}
