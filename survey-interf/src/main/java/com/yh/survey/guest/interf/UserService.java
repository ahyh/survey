package com.yh.survey.guest.interf;

import com.yh.survey.domain.pojo.User;

/**
 * UserService接口
 *
 * @author yanhuan
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return 注册结果：true-成功，false-失败
     */
    Boolean register(User user);

    /**
     * 用户登录
     *
     * @param user 前台输入的用户，包含用户名和密码
     * @return 根据用户名和密码查出来的用户
     */
    User login(User user);
}
