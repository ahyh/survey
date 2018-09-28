package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.pojo.Role;

import java.util.List;

public interface RoleService {
    
    List<Role> findRoleList();

    Integer batchDelete(List<Long> roleIdList, String adminName);

    Integer saveRole(Role role);
}
