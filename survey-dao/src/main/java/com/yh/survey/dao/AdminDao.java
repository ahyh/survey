package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.AdminCondition;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.AdminRoleShip;
import com.yh.survey.domain.manager.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    List<Admin> findAdminList();

    List<Long> findCurrentRoleIdList(Long adminId);

    Integer deleteByAdminId(Long adminId);

    Integer batchInsertAdminRoleShip(@Param("list") List<AdminRoleShip> adminRoleShipList);

    Set<Role> getRoleSetDeeply(Long adminId);
}
