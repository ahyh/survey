package com.yh.survey.guest.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.QuestionDao;
import com.yh.survey.domain.guest.condition.QuestionCondition;
import com.yh.survey.domain.guest.pojo.Question;
import com.yh.survey.guest.interf.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Question服务实现类
 *
 * @author yanhuan
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;


    @Override
    public Integer saveQuestion(Question question) {
        Preconditions.checkNotNull(question);
        return questionDao.insert(question);
    }

    @Override
    public Integer removeQuestion(Long questionId) {
        Preconditions.checkNotNull(questionId);
        return questionDao.delete(questionId);
    }

    @Override
    public Question getQuestionByCondition(QuestionCondition condition) {
        Preconditions.checkNotNull(condition);
        return questionDao.getQuestionByCondition(condition);
    }

    @Override
    public Integer updateQuestion(Question question) {
        Preconditions.checkNotNull(question);
        return questionDao.update(question);
    }

    @Override
    public Integer queryQuestionNumByBagId(Long bagId) {
        Preconditions.checkNotNull(bagId);
        return questionDao.queryQuestionNumByBagId(bagId);
    }

}
