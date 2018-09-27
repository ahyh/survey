package com.yh.survey.dao;

import com.yh.survey.domain.guest.condition.AnswerCondition;
import com.yh.survey.domain.guest.pojo.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserDao
 *
 * @author yanhuan
 */
public interface AnswerDao {

    Integer insert(Answer answer);

    Integer update(Answer answer);

    Integer delete(Long id);

    Answer getAnswerByCondition(AnswerCondition condition);

    Integer insertBatch(@Param("answerList") List<Answer> answerList);

    List<String> findAnswerContentListByQuestionId(Long questionId);

    Integer getQuestionEngageCount(Long questionId);

    Integer getOptionEngageCount(@Param("questionId") Long questionId,@Param("index") Integer index);
}
