package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.pojo.Auth;

import java.util.List;

public interface AuthService {

    List<Auth> findAuthList();

    Integer saveAuth(Auth auth);

    Integer batchDelete(List<Long> authIdList,String updateUser);
}
