package com.yh.survey.log.listeners;

import com.yh.survey.log.RoutingKeyBinder;
import com.yh.survey.log.interf.LogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

/**
 * 日志表创建的监听器
 *
 * @author yanhuan
 */
public class LogCreateTableListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private LogService logService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //1-获取触发事件的IOC容器
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        //2-获取applicationContext的父容器
        ApplicationContext parent = applicationContext.getParent();
        //3-判断是否为Spring容器,parent为null,说明为Spring容器
        if (parent == null) {
            /**
             * 由于数据源绑定器在提交事务后就移除了，所以在每一次开启事务前都需要绑定一次
             */
            RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
            logService.createTable(0);
            RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
            logService.createTable(1);
            RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
            logService.createTable(2);
            RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
            logService.createTable(3);
        }
    }
}
