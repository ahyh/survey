package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.AdminDao;
import com.yh.survey.dao.ResDao;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.AdminRoleShip;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.domain.utils.AuthUtil;
import com.yh.survey.manager.AdminManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Component
public class AdminManagerImpl implements AdminManager {

    @Resource
    private AdminDao adminDao;

    @Resource
    private ResDao resDao;

    /**
     * 更新admin的admin-role关系
     * 1-删除原有的关系
     * 2-插入新的关系
     * 3-更新admin的权限码
     *
     * @param adminId    admin主键
     * @param roleIdList role主键集合
     * @param createUser 操作人
     * @return 受影响的行数，正常情况下返回1
     */
    @Override
    @Transactional
    public Integer updateAdminRoleShip(Long adminId, List<Long> roleIdList, String createUser) {
        Preconditions.checkNotNull(adminId);
        adminDao.deleteByAdminId(adminId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return 0;
        }
        List<AdminRoleShip> adminRoleShipList = Lists.newArrayList();
        AdminRoleShip ship;
        for (Long roleId : roleIdList) {
            ship = new AdminRoleShip();
            ship.setAdminId(adminId);
            ship.setRoleId(roleId);
            ship.setCreateUser(createUser);
            adminRoleShipList.add(ship);
        }
        Integer num = adminDao.batchInsertAdminRoleShip(adminRoleShipList);
        if (num != adminRoleShipList.size()) {
            throw new RuntimeException("insert inner_admin_role error");
        }
        //更新admin表的权限码
        Set<Role> roleSet = adminDao.getRoleSetDeeply(adminId);
        Integer maxResPos = resDao.getMaxResPos();
        String codeArray = AuthUtil.getCodeArray(roleSet, maxResPos);
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setCodeArray(codeArray);
        admin.setUpdateUser(createUser);
        Integer update = adminDao.update(admin);
        if (update != 1) {
            throw new RuntimeException("update admin codeArray error");
        }
        return num;
    }
}
