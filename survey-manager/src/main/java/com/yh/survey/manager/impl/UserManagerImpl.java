package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.ResDao;
import com.yh.survey.dao.UserDao;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.domain.manager.pojo.UserRoleShip;
import com.yh.survey.domain.utils.AuthUtil;
import com.yh.survey.manager.UserManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class UserManagerImpl implements UserManager {

    @Resource
    private UserDao userDao;

    @Resource
    private ResDao resDao;

    @Override
    @Transactional
    public Integer saveUserWithCodeArray(User user, Role role) {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(role);
        if (userDao.insert(user) != 1) {
            throw new RuntimeException("insert user error");
        }
        //iii.保存关联关系
        UserRoleShip userRoleShip = new UserRoleShip();
        userRoleShip.setUserId(user.getId());
        userRoleShip.setRoleId(role.getId());
        userRoleShip.setCreateUser(user.getUsername());
        //iii.保存关联关系
        if (userDao.batchInsertUserRoleShip(Lists.newArrayList(userRoleShip)) != 1) {
            throw new RuntimeException("insert inner_user_role error");
        }
        //iv.根据userId查询Set<Role>
        Set<Role> roleSet = userDao.getRoleSetDeeply(user.getId());
        //v.查询最大权限位值
        Integer maxPos = resDao.getMaxResPos();
        //vi.计算权限码数组值
        user.setCodeArray(AuthUtil.getCodeArray(roleSet, maxPos));
        //更新user表的codeArray
        user.setUpdateUser(user.getCreateUser());
        if (userDao.update(user) != 1) {
            throw new RuntimeException("update user codeArray error");
        }
        return 1;
    }
}
