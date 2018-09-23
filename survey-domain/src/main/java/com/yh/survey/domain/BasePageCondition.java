package com.yh.survey.domain;

/**
 * 带分页的查询条件
 *
 * @author yanhuan
 */
public class BasePageCondition extends BaseDomain {

    private int pageNum;

    private int pageSize;

    private String orderBy;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
