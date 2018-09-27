package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.AdminDao;
import com.yh.survey.domain.manager.condition.AdminCondition;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.manager.service.AdminService;
import com.yh.survey.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;


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
}
