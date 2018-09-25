package com.yh.survey.guest.interf;

import com.yh.survey.domain.condition.QuestionCondition;
import com.yh.survey.domain.pojo.Question;

public interface QuestionService {

    Integer saveQuestion(Question question);

    Integer removeQuestion(Long questionId);

    Question getQuestionByCondition(QuestionCondition condition);

    Integer updateQuestion(Question question);
}
