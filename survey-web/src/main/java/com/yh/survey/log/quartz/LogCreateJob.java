package com.yh.survey.log.quartz;

import com.yh.survey.log.RoutingKeyBinder;
import com.yh.survey.log.interf.LogService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LogCreateJob extends QuartzJobBean {

    private LogService logService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
        logService.createTable(1);

        RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
        logService.createTable(2);

        RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
        logService.createTable(3);
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
