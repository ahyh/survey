package com.yh.survey.interceptors;

import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.domain.utils.AuthUtil;
import com.yh.survey.exceptions.HasNotAuthorityException;
import com.yh.survey.exceptions.UserNotLoginException;
import com.yh.survey.manager.service.ResService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 权限验证拦截器
 *
 * @author yanhuan
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private ResService resService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.将静态资源放过
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        //2.获取Session对象
        HttpSession session = request.getSession();
        //3.获取ServletPath
        String servletPath = request.getServletPath();
        //4.将ServletPath中的多余部分剪掉
        servletPath = AuthUtil.getPath(servletPath);
        //5.根据ServletPath获取对应的Res对象
        ResCondition condition = new ResCondition();
        condition.setServletPath(servletPath);
        Res res = resService.getResByCondition(condition);
        //6.检查是否是公共资源,如果是公共资源则放行
        Byte publicStatus = res.getPublicStatus();
        if (publicStatus.equals(new Byte("1"))) {
            return true;
        }
        //8.如果当前请求的目标地址是User部分的
        if (servletPath.startsWith("/guest")) {
            //9.检查User是否登录
            User user = (User) session.getAttribute("loginUser");
            //10.如果没有登录则抛出自定义异常：UserLoginNeededException
            if (user == null) {
                throw new UserNotLoginException(ExceptionMessage.USER_NOT_LOGIN);
            }
            //11.已登录则检查权限
            String codeArray = user.getCodeArray();
            boolean hasAuthority = AuthUtil.checkAuthority(codeArray, res);
            //12.有权限则放行
            if (hasAuthority) {
                return true;
            } else {
                throw new HasNotAuthorityException(ExceptionMessage.HAS_NOT_AUTHORITY);
            }
        }

        //14.如果当前请求的目标地址是Admin部分的
        if (servletPath.startsWith("/manager")) {
            //15.检查Admin是否登录
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            //16.如果没有登录则抛出自定义异常：AdminLoginNeededException
            if (admin == null) {
                throw new UserNotLoginException(ExceptionMessage.USER_NOT_LOGIN);
            }
            //17.如果已登录则检查是否是超级管理员
            String adminName = admin.getAdminName();
            if ("SuperAdmin".equals(adminName)) {
                return true;
            }
            //18.如果不是超级管理员，则检查是否具备访问目标资源的权限
            String codeArray = admin.getCodeArray();
            boolean hasAuthority = AuthUtil.checkAuthority(codeArray, res);
            if (hasAuthority) {
                //19.有权限则放行
                return true;
            } else {
                //20.没有权限则抛出自定义异常：HasNoRightException
                throw new HasNotAuthorityException(ExceptionMessage.HAS_NOT_AUTHORITY);
            }
        }
        return true;
    }

}
