package com.yh.survey.domain.manager.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * admin表查询条件类
 *
 * @author yanhuan
 */
public class AuthCondition extends BasePageCondition {

    /**
     * 权限名称
     */
    private String authName;

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }
}
