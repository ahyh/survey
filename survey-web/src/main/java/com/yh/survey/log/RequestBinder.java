package com.yh.survey.log;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求绑定器
 * 此类中所有的资源都设置为static的
 * 原因是要保证在线程整个执行过程中ThreadLocal对象是唯一的
 *
 * @author yanhuan
 */
public class RequestBinder {

    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    public static void bindRequest(HttpServletRequest request) {
        local.set(request);
    }

    public static void removeRequest(HttpServletRequest request) {
        local.remove();
    }

    public static HttpServletRequest getRequest() {
        return local.get();
    }
}
