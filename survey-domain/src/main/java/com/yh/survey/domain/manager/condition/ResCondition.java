package com.yh.survey.domain.manager.condition;

import com.yh.survey.domain.BasePageCondition;

/**
 * admin表查询条件类
 *
 * @author yanhuan
 */
public class ResCondition extends BasePageCondition {

    /**
     * 资源路径
     */
    private String servletPath;

    /**
     * 资源权限码
     */
    private Integer resCode;

    /**
     * 资源权限位
     */
    private Integer resPos;

    /**
     * 公共资源标识
     */
    private Byte publicStatus;

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public Integer getResPos() {
        return resPos;
    }

    public void setResPos(Integer resPos) {
        this.resPos = resPos;
    }

    public Byte getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Byte publicStatus) {
        this.publicStatus = publicStatus;
    }
}
