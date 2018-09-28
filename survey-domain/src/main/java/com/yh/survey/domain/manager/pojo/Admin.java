package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

/**
 * admin表映射类
 *
 * @author yanhuan
 */
public class Admin extends BaseDomain {

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 管理员密码
     */
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
