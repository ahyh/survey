package com.yh.survey.service.test;

import com.yh.survey.domain.log.condition.LogCondition;
import com.yh.survey.domain.log.pojo.Log;
import com.yh.survey.log.interf.LogService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class LogServiceTest extends BaseTest {

    @Resource
    private LogService logService;

    @Test
    public void testSave() {
        Log log = new Log();
        log.setOperator("yanhuan");
        log.setOperatorTime(new Date());
        log.setMethodName("savaLog");
        log.setTypeName("LogService");
        log.setInputData("log");
        log.setOutputData("1");
        logService.saveLog(log);
    }

    @Test
    public void testFindList() {
        LogCondition condition = new LogCondition();
        List<Log> logList = logService.findLogList(condition);
        logList.stream().forEach(System.out::println);
    }
}
