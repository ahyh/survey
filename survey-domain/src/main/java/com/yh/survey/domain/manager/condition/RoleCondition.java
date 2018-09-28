package com.yh.survey.domain.manager.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * admin表查询条件类
 *
 * @author yanhuan
 */
public class RoleCondition extends BasePageCondition {

    /**
     * 角色名称
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
