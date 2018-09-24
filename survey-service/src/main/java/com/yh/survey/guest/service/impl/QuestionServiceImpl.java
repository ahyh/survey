package com.yh.survey.guest.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.QuestionDao;
import com.yh.survey.domain.pojo.Question;
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
}
