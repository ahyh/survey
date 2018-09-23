package com.yh.survey.domain.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * User表查询条件
 *
 * @author yanhuan
 */
public class UserCondition extends BasePageCondition {

    private Long id;

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
    private Byte userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }
}
