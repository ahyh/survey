package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Res;

public interface ResService {

    Integer saveRes(Res res);

    Res getResByCondition(ResCondition condition);

    /**
     * 获取最大权限码
     *
     * @param maxPos 最大的权限位
     * @return maxPos的最大权限码
     */
    Integer getMaxResCode(Integer maxPos);

    /**
     * 获取最大的权限位
     *
     * @return 最大的权限位
     */
    Integer getMaxResPos();


}
