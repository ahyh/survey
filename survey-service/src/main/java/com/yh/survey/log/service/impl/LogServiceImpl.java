package com.yh.survey.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yh.survey.dao.LogDao;
import com.yh.survey.domain.log.condition.LogCondition;
import com.yh.survey.domain.log.pojo.Log;
import com.yh.survey.log.interf.LogService;
import com.yh.survey.utils.LogTableNameUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * log服务实现类
 *
 * @author yanhuan
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public Integer saveLog(Log log) {
        Preconditions.checkNotNull(log);
        //1-获取当月的表名
        String tableName = LogTableNameUtil.getTableName(0);
        //2-将当月的日志写入到当月的表中
        return logDao.insert(log, tableName);
    }

    @Override
    public List<Log> findLogList(LogCondition condition) {
        Preconditions.checkNotNull(condition);
        return logDao.findLogList(condition);
    }

    @Override
    public Integer createTable(Integer offset) {
        Preconditions.checkNotNull(offset);
        String tableName = LogTableNameUtil.getTableName(offset);
        return logDao.createTable(tableName);
    }

    @Override
    public PageInfo<Log> findLogPage(LogCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), condition.getOrderBy());
        //1.查询总记录数
        List<Log> logList = logDao.getLimitedLogList(condition);
        if (condition.getPageNum() > 1 && logList.size() == 0) {
            PageHelper.startPage(condition.getPageNum() - 1, condition.getPageSize(), condition.getOrderBy());
            logList = logDao.getLimitedLogList(condition);
        }
        PageInfo<Log> pageInfo = new PageInfo<>(logList);
        return pageInfo;
    }

    @Override
    public List<String> findAllTableNameList() {
        return logDao.findAllTableNameList();
    }
}
