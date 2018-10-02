package com.yh.survey.log.controller;

import com.github.pagehelper.PageInfo;
import com.yh.survey.domain.log.condition.LogCondition;
import com.yh.survey.domain.log.pojo.Log;
import com.yh.survey.log.RoutingKeyBinder;
import com.yh.survey.log.interf.LogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/log")
public class LogController {

    @Resource
    private LogService logService;

    @RequestMapping("/showList")
    public String showList(@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
                           Map<String, Object> map) {
        LogCondition condition = new LogCondition();
        if (StringUtils.isBlank(pageNoStr)) {
            condition.setPageNum(1);
        } else {
            condition.setPageNum(Integer.valueOf(pageNoStr));
        }
        condition.setPageSize(4);
        List<String> tableNames = logService.findAllTableNameList();
        condition.setTableNames(tableNames);
        RoutingKeyBinder.setKey(RoutingKeyBinder.DATASOURCE_LOG);
        PageInfo<Log> page = logService.findLogPage(condition);
        map.put("page", page);
        return "manager/log_list";
    }
}
