package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.BagDao;
import com.yh.survey.dao.QuestionDao;
import com.yh.survey.dao.SurveyDao;
import com.yh.survey.domain.guest.pojo.Bag;
import com.yh.survey.domain.guest.pojo.Question;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.manager.SurveyManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Component
public class SurveyManagerImpl implements SurveyManager {

    @Resource
    private SurveyDao surveyDao;

    @Resource
    private BagDao bagDao;

    @Resource
    private QuestionDao questionDao;

    @Override
    @Transactional
    public Boolean deeplyRemove(Survey survey) {
        Preconditions.checkNotNull(survey);
        List<Long> removeBagIdList = Lists.newArrayList();
        List<Long> removeQuestionIdList = Lists.newArrayList();
        Set<Bag> bagSet = survey.getBagSet();
        if (CollectionUtils.isNotEmpty(bagSet)) {
            for (Bag bag : bagSet) {
                Set<Question> questionSet = bag.getQuestionSet();
                if (CollectionUtils.isNotEmpty(questionSet)) {
                    for (Question question : questionSet) {
                        removeQuestionIdList.add(question.getId());
                    }
                }
                removeBagIdList.add(bag.getId());
            }
        }
        //1-删除所有的问题
        Integer removeQuestionNum = questionDao.deleteBatch(removeQuestionIdList);
        if (removeQuestionNum != removeQuestionIdList.size()) {
            throw new RuntimeException("删除问题出现异常");
        }
        //2-删除所有的包裹
        Integer removeBagNum = bagDao.deleteBatch(removeBagIdList);
        if (removeBagNum != removeBagIdList.size()) {
            throw new RuntimeException("删除包裹出现异常");
        }
        //3-删除调查
        Integer delete = surveyDao.delete(survey.getId());
        if (delete != 1) {
            throw new RuntimeException("删除调查出现异常");
        }
        return true;
    }
}
