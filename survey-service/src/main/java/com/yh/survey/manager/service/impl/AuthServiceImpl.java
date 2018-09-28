package com.yh.survey.manager.service.impl;

import com.yh.survey.dao.AuthDao;
import com.yh.survey.manager.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;


}
