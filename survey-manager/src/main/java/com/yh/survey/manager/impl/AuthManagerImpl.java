package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.AuthDao;
import com.yh.survey.domain.manager.pojo.AuthResShip;
import com.yh.survey.manager.AuthManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AuthManagerImpl implements AuthManager {

    @Resource
    private AuthDao authDao;

    @Override
    public Integer updateAuthResShip(Long authId, List<Long> resIdList, String createUser) {
        Preconditions.checkNotNull(authId);
        authDao.deleteByAuthId(authId);
        if (CollectionUtils.isEmpty(resIdList)) {
            return 0;
        }
        List<AuthResShip> authResShipList = Lists.newArrayList();
        AuthResShip ship;
        for (Long resId : resIdList) {
            ship = new AuthResShip();
            ship.setAuthId(authId);
            ship.setResId(resId);
            ship.setCreateUser(createUser);
            authResShipList.add(ship);
        }
        Integer num = authDao.batchInsertAuthResShip(authResShipList);
        if (num != authResShipList.size()) {
            throw new RuntimeException("insert inner_admin_role error");
        }
        return num;
    }
}
