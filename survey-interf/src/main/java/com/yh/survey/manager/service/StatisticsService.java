package com.yh.survey.manager.service;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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

    /**
     * 根据surveyId获取调查答案的统计信息
     *
     * @param surveyId 调查主键
     * @return workbook
     */
    SXSSFWorkbook getWorkbook(Long surveyId);
}
