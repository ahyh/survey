package com.yh.survey.manager.service;

import java.util.List;

public interface StatisticsService {

    /**
     * 根据questionId获取答案集合
     *
     * @param questionId questionId
     * @return 答案集合
     */
    List<String> findTextResultList(Long questionId);
}
