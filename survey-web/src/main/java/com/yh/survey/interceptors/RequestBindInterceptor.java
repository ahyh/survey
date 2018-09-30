package com.yh.survey.interceptors;

import com.yh.survey.log.RequestBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request绑定拦截器
 *
 * @author yanhuan
 */
public class RequestBindInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //将request绑定在
        RequestBinder.bindRequest(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //处理完请求之后移除绑定的request
        RequestBinder.removeRequest(request);
    }
}
