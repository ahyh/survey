package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.AnswerDao;
import com.yh.survey.manager.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private AnswerDao answerDao;

    @Override
    public List<String> findTextResultList(Long questionId) {
        Preconditions.checkNotNull(questionId);
        return answerDao.findAnswerContentListByQuestionId(questionId);
    }
}
