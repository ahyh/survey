package com.yh.survey.guest.controller;

import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.exceptions.RegisterFailedException;
import com.yh.survey.guest.interf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * User控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/guest")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        try {
            userService.register(user);
            return "/guest/user_login";
        } catch (Exception e) {
            logger.error("UserController register error:{}", e);
            throw new RegisterFailedException(ExceptionMessage.REGISTER_FAILED);
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(User user, HttpSession httpSession) {
        User loginUser = userService.login(user);
        httpSession.setAttribute("loginUser", loginUser);
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:index.jsp";
    }
}
