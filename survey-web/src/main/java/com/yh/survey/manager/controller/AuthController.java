package com.yh.survey.manager.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Auth;
import com.yh.survey.manager.service.AuthService;
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
 * auth控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/manage/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "manager/auth_add";
    }

    /**
     * 权限列表展示页面
     *
     * @param map map域对象，将对象带入到前台页面
     * @return 权限展示页面
     */
    @RequestMapping("/showList")
    public String showList(Map<String, Object> map) {
        List<Auth> authList = authService.findAuthList();
        map.put("authList", authList);
        return "manager/auth_list";
    }

    /**
     * 保存auth
     *
     * @param auth    前台入参
     * @param session
     * @return
     */
    @RequestMapping("/saveAuth")
    public String saveAuth(Auth auth, HttpSession session) {
        Preconditions.checkNotNull(auth);
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        auth.setCreateUser(admin.getAdminName());
        auth.setCreateTime(new Date());
        authService.saveAuth(auth);
        return "redirect:/manage/auth/showList";
    }

    /**
     * 批量删除
     *
     * @param authIdList 需要删除的auth主键的集合
     * @param session    session对象
     * @return 跳转到展示页面
     */
    @RequestMapping("/batchDelete")
    public String batchDelete(@RequestParam("authIdList") List<Long> authIdList, HttpSession session) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(authIdList), "authIdList cannot empty");
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        authService.batchDelete(authIdList, admin.getAdminName());
        return "redirect:/manage/auth/showList";
    }


}
