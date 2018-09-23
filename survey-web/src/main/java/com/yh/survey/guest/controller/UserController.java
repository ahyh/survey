package com.yh.survey.guest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/guest")
public class UserController {

    @RequestMapping("/user/toRegister")
    public String toRegister() {
        return "guest/user_register";
    }

    @RequestMapping("/user/toLogin")
    public String toLogin() {
        return "/guest/user_login";
    }

}
