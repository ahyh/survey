package com.yh.survey.log.interf;

import com.github.pagehelper.PageInfo;
import com.yh.survey.domain.log.condition.LogCondition;
import com.yh.survey.domain.log.pojo.Log;

import java.util.List;

public interface LogService {

    Integer saveLog(Log log);

    List<Log> findLogList(LogCondition condition);

    Integer createTable(Integer offset);

    PageInfo<Log> findLogPage(LogCondition condition);

    List<String> findAllTableNameList();
}
