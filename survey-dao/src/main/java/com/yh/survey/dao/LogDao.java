package com.yh.survey.dao;

import com.yh.survey.domain.log.condition.LogCondition;
import com.yh.survey.domain.log.pojo.Log;
import org.apache.ibatis.annotations.Param;

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

    Integer createTable(@Param("tableName") String tableName);

}
