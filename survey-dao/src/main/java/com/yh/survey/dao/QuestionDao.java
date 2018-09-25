package com.yh.survey.dao;

import com.yh.survey.domain.condition.QuestionCondition;
import com.yh.survey.domain.pojo.Question;

public interface QuestionDao {

    Integer insert(Question question);

    Integer update(Question question);

    Integer delete(Long id);

    Question getQuestionByCondition(QuestionCondition condition);
}
