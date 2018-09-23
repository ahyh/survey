package com.yh.survey.domain.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * User表查询条件
 *
 * @author yanhuan
 */
public class UserCondition extends BasePageCondition {

    /**
     * 用户编号
     */
    private Integer userNo;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否企业用户
     */
    private Byte companyFlag;

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(Byte companyFlag) {
        this.companyFlag = companyFlag;
    }
}
