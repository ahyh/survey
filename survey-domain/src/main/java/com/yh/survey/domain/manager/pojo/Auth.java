package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

import java.util.Set;

/**
 * 权限
 *
 * @author yanhuan
 */
public class Auth extends BaseDomain {

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 资源集合
     */
    private Set<Res> resSet;

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Set<Res> getResSet() {
        return resSet;
    }

    public void setResSet(Set<Res> resSet) {
        this.resSet = resSet;
    }
}
