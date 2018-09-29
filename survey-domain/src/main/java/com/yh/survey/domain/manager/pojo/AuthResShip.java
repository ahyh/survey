package com.yh.survey.domain.manager.pojo;

import com.yh.survey.domain.BaseDomain;

public class AuthResShip extends BaseDomain {

    private Long authId;

    private Long resId;

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }
}
