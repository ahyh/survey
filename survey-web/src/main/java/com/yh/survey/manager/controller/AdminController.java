package com.yh.survey.manager.controller;

import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.exceptions.AdminLoginFailedException;
import com.yh.survey.manager.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/toMain",method = RequestMethod.GET)
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


}
