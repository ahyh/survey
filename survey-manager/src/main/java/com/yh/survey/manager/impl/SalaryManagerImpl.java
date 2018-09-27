package com.yh.survey.manager.impl;

import com.yh.survey.domain.guest.pojo.Salary;
import com.yh.survey.manager.SalaryManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SalaryManagerImpl implements SalaryManager {

    @Transactional
    @Override
    public Integer insertBatch(List<Salary> salaryList) {
        return null;
    }
}
