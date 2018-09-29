package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Role;

import java.util.List;
import java.util.Set;

public interface AdminService {

    Admin login(Admin admin);

    List<Admin> findAdminList();

    Integer saveAdmin(Admin admin);

    List<Long> findCurrentRoleIdList(Long adminId);

    Integer updateAdminRoleShip(Long adminId, List<Long> roleIdList,String createUser);

    Set<Role> getRoleSetDeeply(Long adminId);
}
