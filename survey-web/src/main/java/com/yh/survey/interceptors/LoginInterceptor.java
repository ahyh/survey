package com.yh.survey.interceptors;

import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.exceptions.AdminNotLoginException;
import com.yh.survey.exceptions.UserNotLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * 登录拦截器
 *
 * @author yanhuan
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1-放行静态资源请求
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        //2-公共资源不需要登录
        Set<String> publicResSet = new HashSet<>();
        publicResSet.add("/guest/user/toRegister");
        publicResSet.add("/guest/user/toLogin");
        publicResSet.add("/guest/user/register");
        publicResSet.add("/guest/user/login");
        publicResSet.add("/guest/user/logout");
        publicResSet.add("/manage/admin/toMain");
        publicResSet.add("/manage/admin/toLogin");
        publicResSet.add("/manage/admin/login");
        publicResSet.add("/manage/admin/logout");
        String servletPath = request.getServletPath();
        if (publicResSet.contains(servletPath)) {
            return true;
        }

        //检查当前请求是guest还是manage
        if(servletPath.startsWith("/guest")){
            //3-其他资源需要登录才能操作
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");
            if (loginUser == null) {
                throw new UserNotLoginException(ExceptionMessage.USER_NOT_LOGIN);
            }
        } else if(servletPath.startsWith("/manage")){
            //3-其他资源需要登录才能操作
            HttpSession session = request.getSession();
            Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
            if (loginAdmin == null) {
                throw new AdminNotLoginException(ExceptionMessage.USER_NOT_LOGIN);
            }
        }
        return true;
    }

}
