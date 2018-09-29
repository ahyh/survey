package com.yh.survey.manager.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Auth;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.exceptions.DispatcherFailedException;
import com.yh.survey.manager.service.AuthService;
import com.yh.survey.manager.service.ResService;
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
 * auth控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/manage/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private AuthService authService;

    @Resource
    private ResService resService;

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

    /**
     * 跳转到分配资源页面
     *
     * @param authId auth主键
     * @param map    将对象带到前台浏览器进行展示
     * @return 跳转到分配资源页面
     */
    @RequestMapping("/toDispatcher/{authId}")
    public String toDispatcher(@PathVariable("authId") Long authId, Map<String, Object> map) {
        List<Res> resList = resService.findResList();
        List<Long> currentIdList = authService.findCurrentResIdList(authId);
        map.put("resList", resList);
        map.put("currentIdList", currentIdList);
        return "manager/dispatcher_auth_res";
    }

    /**
     * 分配资源
     */
    @RequestMapping("/dispatcher")
    public String dispatcher(@RequestParam("authId") Long authId,
                               @RequestParam(value = "resIdList", required = false) List<Long> resIdList,
                               HttpSession session) {
        try {
            Preconditions.checkNotNull(authId);
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            authService.updateAuthResShip(authId, resIdList, admin.getAdminName());
            return "redirect:/manage/auth/showList";
        } catch (Exception e) {
            logger.error("AuthController doDispatcher error:{}", e);
            throw new DispatcherFailedException(ExceptionMessage.DISPATCHER_FAILED);
        }
    }


}
