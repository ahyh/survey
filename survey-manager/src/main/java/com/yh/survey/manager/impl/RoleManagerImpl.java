package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.RoleDao;
import com.yh.survey.domain.manager.pojo.RoleAuthShip;
import com.yh.survey.manager.RoleManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RoleManagerImpl implements RoleManager {

    @Resource
    private RoleDao roleDao;

    @Override
    public Integer updateRoleAuthShip(Long roleId, List<Long> authIdList, String createUser) {
        Preconditions.checkNotNull(roleId);
        roleDao.deleteByRoleId(roleId);
        if (CollectionUtils.isEmpty(authIdList)) {
            return 0;
        }
        List<RoleAuthShip> roleAuthShipList = Lists.newArrayList();
        RoleAuthShip ship;
        for (Long authId : authIdList) {
            ship = new RoleAuthShip();
            ship.setAuthId(authId);
            ship.setRoleId(roleId);
            ship.setCreateUser(createUser);
            roleAuthShipList.add(ship);
        }
        Integer num = roleDao.batchInsertRoleAuthShip(roleAuthShipList);
        if (num != roleAuthShipList.size()) {
            throw new RuntimeException("insert inner_admin_role error");
        }
        return num;
    }
}
