package com.yh.survey.domain.manager.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * admin表查询条件类
 *
 * @author yanhuan
 */
public class AdminCondition extends BasePageCondition {

    private String adminName;

    private String password;

    /**
     * 用户权限码
     */
    private String codeArray;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeArray() {
        return codeArray;
    }

    public void setCodeArray(String codeArray) {
        this.codeArray = codeArray;
    }
}
