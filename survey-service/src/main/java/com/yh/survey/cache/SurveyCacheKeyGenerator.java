package com.yh.survey.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 键生成器
 *
 * @author yanhuan
 */
@Component
public class SurveyCacheKeyGenerator implements KeyGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SurveyCacheKeyGenerator.class);

    /**
     * @param target 调用目标方法的目标对象
     * @param method 目标方法
     * @param args   给目标方法传入的参数
     * @return
     */
    @Override
    public Object generate(Object target, Method method, Object... args) {
        StringBuffer sb = new StringBuffer();
        //1-获取目标对象的全类名
        String className = target.getClass().getName();
        //2-获取目标方法的方法名
        String methodName = method.getName();
        sb.append(className).append(".").append(methodName);
        //3-传入参数拼装成字符串
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                sb.append(".").append(args[i]);
            }
        }
        logger.error("SurveyCacheKeyGenerator generate key:{}", sb.toString());
        return sb.toString();
    }
}
