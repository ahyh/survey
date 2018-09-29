package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.AdminDao;
import com.yh.survey.domain.manager.condition.AdminCondition;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.manager.AdminManager;
import com.yh.survey.manager.service.AdminService;
import com.yh.survey.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * AdminManager
 *
 * @author yanhuan
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Resource
    private AdminManager adminManager;

    @Override
    public Admin login(Admin admin) {
        Preconditions.checkNotNull(admin);
        Preconditions.checkArgument(StringUtils.isNotBlank(admin.getAdminName()), "adminName cannot empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(admin.getPassword()), "password cannot empty!");
        AdminCondition condition = new AdminCondition();
        condition.setAdminName(admin.getAdminName());
        condition.setPassword(MD5Util.md5(admin.getPassword()));
        Admin loginAdmin = adminDao.getAdminByCondition(condition);
        Preconditions.checkArgument(loginAdmin != null, "adminName and password cannot matching");
        return loginAdmin;
    }

    @Override
    public List<Admin> findAdminList() {
        return adminDao.findAdminList();
    }

    @Override
    public Integer saveAdmin(Admin admin) {
        Preconditions.checkNotNull(admin);
        return adminDao.insert(admin);
    }

    @Override
    public List<Long> findCurrentRoleIdList(Long adminId) {
        Preconditions.checkNotNull(adminId);
        return adminDao.findCurrentRoleIdList(adminId);
    }

    @Override
    public Integer updateAdminRoleShip(Long adminId, List<Long> roleIdList, String createUser) {
        Preconditions.checkNotNull(adminId);
        return adminManager.updateAdminRoleShip(adminId, roleIdList, createUser);
    }

    @Override
    public Set<Role> getRoleSetDeeply(Long adminId) {
        Preconditions.checkNotNull(adminId);
        return adminDao.getRoleSetDeeply(adminId);
    }
}
