package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

public class RoleAuthShip extends BaseDomain {

    private Long roleId;

    private Long authId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }
}
