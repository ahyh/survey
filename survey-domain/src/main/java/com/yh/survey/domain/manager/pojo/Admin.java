package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

/**
 * admin表映射类
 *
 * @author yanhuan
 */
public class Admin extends BaseDomain {

    private String adminName;

    private String password;

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
}
