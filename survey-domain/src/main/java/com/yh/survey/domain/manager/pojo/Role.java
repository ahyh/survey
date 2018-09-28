package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

import java.util.Set;

/**
 * 角色
 *
 * @author yanhuan
 */
public class Role extends BaseDomain {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限集合
     */
    private Set<Auth> authSet;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Auth> getAuthSet() {
        return authSet;
    }

    public void setAuthSet(Set<Auth> authSet) {
        this.authSet = authSet;
    }
}
