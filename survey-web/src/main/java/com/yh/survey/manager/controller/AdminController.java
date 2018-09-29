package com.yh.survey.manager.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.exceptions.AdminLoginFailedException;
import com.yh.survey.exceptions.DispatcherFailedException;
import com.yh.survey.manager.service.AdminService;
import com.yh.survey.manager.service.RoleService;
import com.yh.survey.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/manage/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/toMain", method = RequestMethod.GET)
    public String toMain() {
        return "manager/manager_main";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "manager/admin_login";
    }

    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session) {
        try {
            Admin loginAdmin = adminService.login(admin);
            session.setAttribute("loginAdmin", loginAdmin);
            return "redirect:/manage/admin/toMain";
        } catch (Exception e) {
            logger.error("AdminController login error:{}", e);
            throw new AdminLoginFailedException(ExceptionMessage.ADMIN_LOGIN_FAILED);
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginAdmin");
        return "redirect:/manage/admin/toMain";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "manager/admin_add";
    }

    @RequestMapping("/saveAdmin")
    public String saveAdmin(Admin admin, HttpSession session) {
        Preconditions.checkNotNull(admin);
        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        admin.setCreateUser(loginAdmin.getAdminName());
        admin.setCreateTime(new Date());
        admin.setPassword(MD5Util.md5(admin.getPassword()));
        adminService.saveAdmin(admin);
        return "redirect:/manage/admin/showList";
    }

    @RequestMapping("/showList")
    public String showList(Map<String, Object> map) {
        List<Admin> adminList = adminService.findAdminList();
        map.put("adminList", adminList);
        return "manager/admin_list";
    }

    @RequestMapping("/toDispatcher/{adminId}")
    public String toDispatcher(@PathVariable("adminId") Long adminId, Map<String, Object> map) {
        //1-查询所有的角色
        List<Role> roleList = roleService.findRoleList();
        //2-查询当前管理员具有的角色信息
        List<Long> currentRoleIdList = adminService.findCurrentRoleIdList(adminId);
        map.put("roleList", roleList);
        map.put("roleIdList", currentRoleIdList);
        return "manager/dispatcher_admin_role";
    }

    /**
     * 分配角色
     */
    @RequestMapping("/doDispatcher")
    public String doDispatcher(@RequestParam("adminId") Long adminId,
                               @RequestParam(value = "roleIdList", required = false) List<Long> roleIdList,
                               HttpSession session) {
        try {
            Preconditions.checkNotNull(adminId);
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            adminService.updateAdminRoleShip(adminId, roleIdList, admin.getAdminName());
            return "redirect:/manage/admin/showList";
        } catch (Exception e) {
            logger.error("AdminController doDispatcher error:{}", e);
            throw new DispatcherFailedException(ExceptionMessage.DISPATCHER_FAILED);
        }
    }


}
