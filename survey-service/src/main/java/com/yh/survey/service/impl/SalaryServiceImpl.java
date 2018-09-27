package com.yh.survey.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yh.survey.dao.SalaryDao;
import com.yh.survey.domain.guest.condition.SalaryCondition;
import com.yh.survey.domain.guest.pojo.Salary;
import com.yh.survey.interf.SalaryService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SalaryService服务：基本的增删改查方法
 * Created by yanhuan1 on 2018/1/16.
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    private static final Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);

    @Resource
    private SalaryDao salaryDao;



    @Override
    public Integer insert(Salary salary) {
        Preconditions.checkNotNull(salary);
        return salaryDao.insert(salary);
    }

    @Override
    public Integer update(Salary salary) {
        Preconditions.checkNotNull(salary);
        return salaryDao.update(salary);
    }

    @Override
    public Integer delete(Long id) {
        return salaryDao.delete(id);
    }

    @Override
    public Salary getSalaryByCondition(SalaryCondition condition) {
        Preconditions.checkArgument(condition != null, "condition cannot null!");
        logger.error("测试slf4j,id:{},name:{}", condition.getId(), condition.getName());
        Salary salary = salaryDao.getSalaryByCondition(condition);
        return salary;
    }

    @Override
    public List<Salary> findSalaryList(SalaryCondition condition) {
        return salaryDao.findSalaryList(condition);
    }

    @Override
    public PageInfo<Salary> selectSalaryPage(SalaryCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), condition.getOrderBy());
        List<Salary> salaries = salaryDao.findSalaryPage(condition);
        PageInfo<Salary> pageInfo = new PageInfo<>(salaries);
        return pageInfo;
    }

    @Override
    public Integer batchInsert(List<Salary> salaryList) {
        if (CollectionUtils.isNotEmpty(salaryList)) {
            return salaryDao.batchInsert(salaryList);
        }
        return 0;
    }

    @Override
    public Integer batchDelete(List<Long> idList) {
        if (CollectionUtils.isNotEmpty(idList)) {
            return salaryDao.batchDelete(idList);
        }
        return 0;
    }

    @Override
    public Integer batchInsertOrUpdate(List<Salary> salaryList) {
        if (CollectionUtils.isNotEmpty(salaryList)) {
            return salaryDao.batchInsertOrUpdate(salaryList);
        }
        return 0;
    }

    @Override
    public Integer deletePhysics(Long id) {
        return salaryDao.deletePhysics(id);
    }

    @Override
    public List<Salary> findByDateSub(Integer days) {
        return salaryDao.findByDateSub(days);
    }

    @Override
    public List<Salary> findSalaryListBySalaryList(List<Salary> salaryList) {
        return salaryDao.findSalaryListBySalaryList(salaryList);
    }

    /**
     * 根据条件更新Salary
     *
     * @param condition 更新条件
     * @return 受影响的行数
     */
    @Override
    public Integer updateSalaryByCondition(SalaryCondition condition) {
        Preconditions.checkNotNull(condition);
        Integer integer = salaryDao.updateByCondition(condition);
        return integer;
    }

}
