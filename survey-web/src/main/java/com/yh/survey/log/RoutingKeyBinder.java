package com.yh.survey.log;

/**
 * 数据源key绑定器
 *
 * @author yanhuan
 */
public class RoutingKeyBinder {

    public static final String DATASOURCE_MAIN = "DATASOURCE_MAIN";
    public static final String DATASOURCE_LOG = "DATASOURCE_LOG";

    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static String getKey() {
        return local.get();
    }

    public static void setKey(String value) {
        local.set(value);
    }

    public static void removeKey() {
        local.remove();
    }
}
