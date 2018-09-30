package com.yh.survey.dao;

import com.yh.survey.domain.log.condition.LogCondition;
import com.yh.survey.domain.log.pojo.Log;

import java.util.List;

/**
 * UserDao
 *
 * @author yanhuan
 */
public interface LogDao {

    Integer insert(Log log);

    Log getLogByCondition(LogCondition condition);

    List<Log> findLogList(LogCondition condition);

}
