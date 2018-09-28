package com.yh.survey.manager.service.impl;

import com.yh.survey.dao.RoleDao;
import com.yh.survey.manager.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

}
