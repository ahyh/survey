package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.AuthDao;
import com.yh.survey.domain.manager.pojo.Auth;
import com.yh.survey.manager.AuthManager;
import com.yh.survey.manager.service.AuthService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    @Resource
    private AuthManager authManager;

    @Override
    public List<Auth> findAuthList() {
        return authDao.findAuthList();
    }

    @Override
    public Integer saveAuth(Auth auth) {
        Preconditions.checkNotNull(auth);
        return authDao.insert(auth);
    }

    @Override
    public Integer batchDelete(List<Long> authIdList, String updateUser) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(authIdList), "authIdList cannot empty");
        return authDao.batchDelete(authIdList, updateUser);
    }

    @Override
    public List<Long> findCurrentResIdList(Long authId) {
        Preconditions.checkNotNull(authId);
        return authDao.findCurrentResIdList(authId);
    }

    @Override
    public Integer updateAuthResShip(Long authId, List<Long> resIdList, String adminName) {
        Preconditions.checkNotNull(authId);
        return authManager.updateAuthResShip(authId, resIdList, adminName);
    }
}
