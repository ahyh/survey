package com.yh.survey.log.router;

import com.yh.survey.log.RoutingKeyBinder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 路由数据源
 *
 * @author yanhuan
 */
public class SurveyLogRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //1-从当前线程上获取具体数据源对应的key值
        String key = RoutingKeyBinder.getKey();
        /**
         * 2 -key使用完成后要立即移除
         */
        RoutingKeyBinder.removeKey();
        return key;
    }
}
