package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.pojo.Auth;

import java.util.List;

public interface AuthService {

    List<Auth> findAuthList();

    Integer saveAuth(Auth auth);

    Integer batchDelete(List<Long> authIdList, String updateUser);

    /**
     * 根据authId查询相关联的resId的集合
     *
     * @param authId authid
     * @return resId集合
     */
    List<Long> findCurrentResIdList(Long authId);

    Integer updateAuthResShip(Long authId, List<Long> resIdList, String adminName);
}
