package com.yh.survey.manager.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Auth;
import com.yh.survey.domain.manager.pojo.Role;
import com.yh.survey.exceptions.DispatcherFailedException;
import com.yh.survey.manager.service.AuthService;
import com.yh.survey.manager.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    @Resource
    private AuthService authService;

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

    /**
     * 跳转到分配资源页面
     *
     * @param roleId role主键
     * @param map    将对象带到前台浏览器进行展示
     * @return 跳转到分配资源页面
     */
    @RequestMapping("/toDispatcher/{roleId}")
    public String toDispatcher(@PathVariable("roleId") Long roleId, Map<String, Object> map) {
        List<Auth> authList = authService.findAuthList();
        List<Long> currentIdList = roleService.findCurrentAuthIdList(roleId);
        map.put("authList", authList);
        map.put("currentIdList", currentIdList);
        return "manager/dispatcher_role_auth";
    }

    /**
     * 分配资源
     */
    @RequestMapping("/dispatcher")
    public String dispatcher(@RequestParam("roleId") Long roleId,
                               @RequestParam(value = "authIdList", required = false) List<Long> authIdList,
                               HttpSession session) {
        try {
            Preconditions.checkNotNull(roleId);
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            roleService.updateRoleAuthShip(roleId, authIdList, admin.getAdminName());
            return "redirect:/manage/role/showList";
        } catch (Exception e) {
            logger.error("RoleController doDispatcher error:{}", e);
            throw new DispatcherFailedException(ExceptionMessage.DISPATCHER_FAILED);
        }
    }


}
