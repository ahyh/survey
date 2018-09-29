package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.AdminDao;
import com.yh.survey.dao.AuthDao;
import com.yh.survey.dao.ResDao;
import com.yh.survey.dao.UserDao;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.AuthResShip;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.domain.utils.AuthUtil;
import com.yh.survey.manager.AuthManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Component
public class AuthManagerImpl implements AuthManager {

    @Resource
    private AuthDao authDao;

    @Resource
    private ResDao resDao;

    @Resource
    private AdminDao adminDao;

    @Resource
    private UserDao userDao;

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
        updateAllCodeArray();
        return num;
    }

    @Override
    @Transactional
    public Integer updateAllCodeArray() {
        //重新计算所有用户的权限码数组
        Integer maxPos = resDao.getMaxResPos();
        //1.所有的Admin
        //①查询所有的Admin对象
        List<Admin> adminList = adminDao.findAdminList();
        //②遍历集合
        Long adminId,userId;
        Set<Role> roleSet;
        for (Admin admin : adminList) {
            adminId = admin.getId();
            roleSet = adminDao.getRoleSetDeeply(adminId);
            admin.setCodeArray(AuthUtil.getCodeArray(roleSet, maxPos));
            adminDao.update(admin);
        }

        //2.所有的User
        List<User> userList = userDao.findUserList();
        for (User user : userList) {
            userId = user.getId();
            roleSet = userDao.getRoleSetDeeply(userId);
            user.setCodeArray(AuthUtil.getCodeArray(roleSet, maxPos));
            userDao.update(user);
        }
        return 1;
    }
}
