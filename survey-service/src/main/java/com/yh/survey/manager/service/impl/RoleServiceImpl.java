package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.RoleDao;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.manager.RoleManager;
import com.yh.survey.manager.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private RoleManager roleManager;

    @Override
    public List<Role> findRoleList() {
        return roleDao.findRoleList();
    }

    @Override
    public Integer batchDelete(List<Long> roleIdList, String adminName) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(roleIdList), "roleIdList cannot empty");
        return roleDao.batchDelete(roleIdList, adminName);
    }

    @Override
    public Integer saveRole(Role role) {
        Preconditions.checkNotNull(role);
        return roleDao.insert(role);
    }

    @Override
    public List<Long> findCurrentAuthIdList(Long roleId) {
        Preconditions.checkNotNull(roleId);
        return roleDao.findCurrentAuthIdList(roleId);
    }

    @Override
    public Integer updateRoleAuthShip(Long roleId, List<Long> authIdList, String adminName) {
        Preconditions.checkNotNull(roleId);
        return roleManager.updateRoleAuthShip(roleId, authIdList, adminName);
    }
}
