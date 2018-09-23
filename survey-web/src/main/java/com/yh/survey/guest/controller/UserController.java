package com.yh.survey.guest.controller;

import com.yh.survey.domain.pojo.User;
import com.yh.survey.guest.interf.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * User控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/guest")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/user/toRegister")
    public String toRegister() {
        return "guest/user_register";
    }

    @RequestMapping("/user/toLogin")
    public String toLogin() {
        return "/guest/user_login";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(User user) {
        userService.register(user);
        return "/guest/user_login";
    }

}
