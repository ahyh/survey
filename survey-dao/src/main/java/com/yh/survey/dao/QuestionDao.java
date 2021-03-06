package com.yh.survey.dao;

import com.yh.survey.domain.guest.condition.QuestionCondition;
import com.yh.survey.domain.guest.pojo.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface QuestionDao {

    Integer insert(Question question);

    Integer update(Question question);

    Integer delete(Long id);

    Question getQuestionByCondition(QuestionCondition condition);

    Integer queryQuestionNumByBagId(Long bagId);

    Integer deleteByCondition(QuestionCondition questionCondition);

    Integer insertBatch(@Param("questionSet") Set<Question> questionSet);

    Integer deleteBatch(@Param("list") List<Long> idList);
}
