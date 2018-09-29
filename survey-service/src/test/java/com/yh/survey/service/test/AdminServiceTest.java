package com.yh.survey.service.test;

import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.manager.service.AdminService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Set;

public class AdminServiceTest extends BaseTest {

    @Resource
    private AdminService adminService;

    @Test
    public void testGetRoleSetByAdminId(){
        Long adminId = 2L;
        Set<Role> roleSetDeeply = adminService.getRoleSetDeeply(adminId);
        System.out.println(roleSetDeeply);
    }
}
