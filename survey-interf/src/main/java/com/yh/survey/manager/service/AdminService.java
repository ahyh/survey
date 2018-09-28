package com.yh.survey.manager.service;

import com.yh.survey.domain.manager.pojo.Admin;

import java.util.List;

public interface AdminService {

    Admin login(Admin admin);

    List<Admin> findAdminList();

    Integer saveAdmin(Admin admin);
}
