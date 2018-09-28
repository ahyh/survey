package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Res;

import java.util.List;

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

    /**
     * 获取资源集合
     *
     * @return 资源集合
     */
    List<Res> findResList();

    /**
     * 更新res表的publicStatus状态，并将更新后的publicStatus返回
     *
     * @param resId      resId
     * @param updateUser 更新操作人
     * @return 更新后的publicStatus
     */
    Byte updatePublicStatus(Long resId, String updateUser);

    /**
     * 批量删除res资源
     *
     * @param resIdList 需要删除的res的主键集合
     * @return 删除的资源数量
     */
    Integer batchDelete(List<Long> resIdList,String updateUser);

}
