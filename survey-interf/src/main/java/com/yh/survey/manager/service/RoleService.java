package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleList();

    Integer batchDelete(List<Long> roleIdList, String adminName);

    Integer saveRole(Role role);

    /**
     * 根据roleId获取authId集合
     *
     * @param roleId roleId
     * @return authId集合
     */
    List<Long> findCurrentAuthIdList(Long roleId);

    Integer updateRoleAuthShip(Long roleId, List<Long> authIdList, String adminName);
}
