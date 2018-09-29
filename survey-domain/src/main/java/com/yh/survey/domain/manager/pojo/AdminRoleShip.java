package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

public class AdminRoleShip extends BaseDomain {

    private Long adminId;

    private Long roleId;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
