package com.yh.survey.interceptors;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.manager.service.ResService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class ResInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private ResService resService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        String servletPath = request.getServletPath();
        servletPath = getPath(servletPath);
        ResCondition condition = new ResCondition();
        condition.setServletPath(servletPath);
        Res res = resService.getResByCondition(condition);
        if (res != null) {
            return true;
        }
        Integer resCode, resPos;
        Integer maxCode = 1 << 30;
        Res newRes = new Res();
        newRes.setServletPath(servletPath);
        //获取最大的权限位
        Integer maxResPos = resService.getMaxResPos();
        Integer maxResCode = (maxResPos == null) ? null : resService.getMaxResCode(maxResPos);
        //如果maxResCode为null说明是第一次保存
        if (maxResCode == null) {
            resCode = 1;
            resPos = 0;
        } else if (maxResCode.equals(maxCode)) {
            resPos = maxResPos + 1;
            resCode = 1;
        } else {
            resCode = maxResCode << 1;
            resPos = maxResPos;
        }
        newRes.setResCode(resCode);
        newRes.setResPos(resPos);
        newRes.setPublicStatus((byte) 0);
        newRes.setCreateTime(new Date());
        newRes.setCreateUser("system");
        resService.saveRes(newRes);
        return true;
    }

    private String getPath(String servletPath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(servletPath), "servletPath cannot blank");
        String[] splitStrs = servletPath.split("/");
        if (splitStrs.length <= 4) {
            return servletPath;
        }
        return "/" + splitStrs[1] + "/" + splitStrs[2] + "/" + splitStrs[3];
    }
}
