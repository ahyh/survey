package com.yh.survey.tag;

import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.domain.utils.AuthUtil;
import com.yh.survey.log.RoutingKeyBinder;
import com.yh.survey.manager.service.ResService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 权限标签处理类
 *
 * @author yanhuan
 */
public class AuthTag extends SimpleTagSupport {

    private String servletPath;

    /**
     * 重写doTag方法用于实现自定义标签
     *
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        //1.准备工作
        RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_MAIN);
        PageContext pageContext = (PageContext) getJspContext();
        HttpSession session = pageContext.getSession();
        ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //3.从IOC容器中获取ResService对象
        ResService resService = applicationContext.getBean(ResService.class);
        //4.查询当前servletPath对应的Res对象
        ResCondition condition = new ResCondition();
        condition.setServletPath(servletPath);
        Res res = resService.getResByCondition(condition);
        //5.检查当前资源是否是公共资源
        Byte publicStatus = res.getPublicStatus();
        if (publicStatus.equals(new Byte("1"))) {
            //6.如果是公共资源，则直接显示标签体，并让函数停止执行
            getJspBody().invoke(null);
            return;
        }
        //7.检查当前ServletPath是否是guest部分
        if (servletPath.startsWith("/guest")) {
            //检查User是否登录
            User user = (User) session.getAttribute("loginUser");
            if (user != null) {
                //②如果已经登录，则检查是否具备权限
                String codeArray = user.getCodeArray();
                boolean hasAuthority = AuthUtil.checkAuthority(codeArray, res);
                if (hasAuthority) {
                    //③如果有权限，则执行标签体并停止函数执行
                    getJspBody().invoke(null);
                    return;
                }
            }
        }

        //8.检查当前ServletPath是否是manager部分
        if (servletPath.startsWith("/manage")) {
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            if (admin != null) {
                //检查是否是超级管理员
                String adminName = admin.getAdminName();
                if ("SuperAdmin".equals(adminName)) {
                    getJspBody().invoke(null);
                    return;
                }
                String codeArray = admin.getCodeArray();
                boolean hasAuthority = AuthUtil.checkAuthority(codeArray, res);
                if (hasAuthority) {
                    getJspBody().invoke(null);
                    return;
                }
            }
        }
    }

    public void setServletPath(String servletPath) {
        this.servletPath = "/" + servletPath;
    }

}
