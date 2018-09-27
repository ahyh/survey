package com.yh.survey.manager.service;

import org.jfree.chart.JFreeChart;

import java.util.List;

public interface StatisticsService {

    /**
     * 根据questionId获取答案集合
     *
     * @param questionId questionId
     * @return 答案集合
     */
    List<String> findTextResultList(Long questionId);

    /**
     * 根据questionId获取
     *
     * @param questionId questionId
     * @return chart对象
     */
    JFreeChart getChart(Long questionId);
}
