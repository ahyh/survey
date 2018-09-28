package com.yh.survey.manager.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.manager.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Role控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/manage/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "manager/role_add";
    }

    @RequestMapping("/showList")
    public String showList(Map<String, Object> map) {
        List<Role> roleList = roleService.findRoleList();
        map.put("roleList", roleList);
        return "manager/role_list";
    }

    @RequestMapping("/batchDelete")
    public String batchDelete(@RequestParam("roleIdList") List<Long> roleIdList, HttpSession session) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(roleIdList), "roleIdList cannot empty");
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        roleService.batchDelete(roleIdList, admin.getAdminName());
        return "redirect:/manage/role/showList";
    }

    @RequestMapping("/saveRole")
    public String saveRole(Role role, HttpSession session) {
        Preconditions.checkNotNull(role);
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        role.setCreateTime(new Date());
        role.setCreateUser(admin.getAdminName());
        roleService.saveRole(role);
        return "redirect:/manage/role/showList";
    }


}
