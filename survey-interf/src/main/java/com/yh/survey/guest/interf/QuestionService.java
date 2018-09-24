package com.yh.survey.guest.interf;

import com.yh.survey.domain.pojo.Question;

public interface QuestionService {

    Integer saveQuestion(Question question);

    Integer removeQuestion(Long questionId);
}
