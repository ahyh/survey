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
}
