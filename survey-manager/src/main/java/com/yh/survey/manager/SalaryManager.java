package com.yh.survey.manager;

import com.yh.survey.domain.guest.pojo.Salary;

import java.util.List;

public interface SalaryManager {

    Integer insertBatch(List<Salary> salaryList);
}
