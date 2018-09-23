package com.yh.survey.guest.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.dao.UserDao;
import com.yh.survey.domain.condition.UserCondition;
import com.yh.survey.domain.pojo.User;
import com.yh.survey.exceptions.UserLoginFailedException;
import com.yh.survey.exceptions.UserNameAlreadyExistsException;
import com.yh.survey.guest.interf.UserService;
import com.yh.survey.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * UserService实现类
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 注册
     *
     * @param user 用户对象
     * @return 注册结果：true-注册成功，false-注册失败
     */
    @Override
    public Boolean register(User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()));
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()));
        //1-检查用户名书否被占用
        user.setUserNo(user.getUsername());
        UserCondition condition = new UserCondition();
        condition.setUsername(user.getUsername());
        User userByCondition = userDao.getUserByCondition(condition);
        //2-如果没有被占用则保存user对象
        if (userByCondition != null) {
            throw new UserNameAlreadyExistsException("用户名已经存在,请重新注册!");
        }
        user.setCreateTime(new Date());
        user.setCreateUser(user.getUsername());
        //密码加密
        user.setPassword(MD5Util.md5(user.getPassword()));
        if (userDao.insert(user) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 用户登录
     *
     * @param user 前台输入的用户，包含用户名和密码
     * @return 根据用户名和密码查出来的用户对象
     */
    @Override
    public User login(User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()), "用户名不能为空!");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空!");
        UserCondition condition = new UserCondition();
        condition.setUsername(user.getUsername());
        condition.setPassword(MD5Util.md5(user.getPassword()));
        User userByCondition = userDao.getUserByCondition(condition);
        if (userByCondition == null) {
            throw new UserLoginFailedException(ExceptionMessage.USER_LOGIN_FAILED);
        }
        return userByCondition;
    }
}
