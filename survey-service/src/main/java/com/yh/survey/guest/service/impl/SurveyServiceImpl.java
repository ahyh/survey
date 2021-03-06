package com.yh.survey.guest.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yh.survey.dao.SurveyDao;
import com.yh.survey.domain.guest.condition.SurveyCondition;
import com.yh.survey.domain.guest.pojo.Bag;
import com.yh.survey.domain.guest.pojo.Question;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.guest.interf.SurveyService;
import com.yh.survey.manager.SurveyManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Resource
    private SurveyDao surveyDao;

    @Resource
    private SurveyManager surveyManager;

    @Override
    public Integer insert(Survey survey) {
        Preconditions.checkNotNull(survey);
        return surveyDao.insert(survey);
    }

    /**
     * 查询分页方法
     *
     * @param condition 查询条件
     * @return 分页对象
     */
    @Override
    public PageInfo<Survey> findSurveyPage(SurveyCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), condition.getOrderBy());
        List<Survey> surveyList = surveyDao.findSurveyList(condition);
        if (condition.getPageNum() > 1 && surveyList.size() == 0) {
            PageHelper.startPage(condition.getPageNum() - 1, condition.getPageSize(), condition.getOrderBy());
            surveyList = surveyDao.findSurveyList(condition);
        }
        PageInfo<Survey> pageInfo = new PageInfo<>(surveyList);
        return pageInfo;
    }

    @Override
    public Integer removeSurvey(Long id) {
        Preconditions.checkNotNull(id);
        return surveyDao.delete(id);
    }

    @Override
    public Survey getSurveyByCondition(SurveyCondition condition) {
        Preconditions.checkNotNull(condition);
        return surveyDao.getSurveyByCondition(condition);
    }

    @Override
    public Integer updateSurvey(Survey survey) {
        Preconditions.checkNotNull(survey);
        return surveyDao.update(survey);
    }

    @Override
    public Survey getSurveyWithBagAndQuestions(Long id) {
        Preconditions.checkNotNull(id);
        Survey survey = surveyDao.getSurveyWithBagAndQuestions(id);
        Preconditions.checkNotNull(survey);
        if(CollectionUtils.isEmpty(survey.getBagSet())){
            return survey;
        }
        LinkedHashSet<Bag> bagSet = survey.getBagSet();
        for (Bag bag : bagSet) {
            LinkedHashSet<Question> questionSet = bag.getQuestionSet();
            if (CollectionUtils.isNotEmpty(questionSet) && questionSet.size() == 1) {
                for (Question question : questionSet) {
                    if (StringUtils.isBlank(question.getQuestionName()) || question.getIsDelete().equals(new Byte("1"))) {
                        bag.setQuestionSet(null);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(bag.getQuestionSet())) {
                Iterator<Question> iterator = bag.getQuestionSet().iterator();
                while (iterator.hasNext()) {
                    Question next = iterator.next();
                    if (next.getIsDelete().equals(new Byte("1"))) {
                        iterator.remove();
                    }
                }
            }
        }
        //如果包裹是已经删除的则去掉
        Iterator<Bag> bagIterator = bagSet.iterator();
        while(bagIterator.hasNext()){
            Bag next = bagIterator.next();
            if(next.getIsDelete().equals(new Byte("1"))){
                bagIterator.remove();
            }
        }
        return survey;
    }

    @Override
    public Integer updateSurveyComplete(Survey survey) {
        Preconditions.checkNotNull(survey);
        Survey tempSurvey = surveyDao.getSurveyWithBagAndQuestions(survey.getId());
        if (tempSurvey == null) {
            throw new RuntimeException("survey cannot null");
        }
        if (CollectionUtils.isEmpty(tempSurvey.getBagSet())) {
            throw new RuntimeException("survey has no bagSet");
        }
        Integer deleteQuestionNum;
        for (Bag bag : tempSurvey.getBagSet()) {
            if (CollectionUtils.isEmpty(bag.getQuestionSet())) {
                throw new RuntimeException("bag has no questionSet");
            }
            deleteQuestionNum = 0;
            for (Question question : bag.getQuestionSet()) {
                if (StringUtils.isBlank(question.getQuestionName())) {
                    throw new RuntimeException("questionName cannot blank");
                }
                if (question.getIsDelete().equals(new Byte("1"))) {
                    deleteQuestionNum++;
                }
            }
            if (deleteQuestionNum == bag.getQuestionSet().size()) {
                throw new RuntimeException("all question deleted");
            }
        }
        return surveyDao.update(survey);
    }

    @Override
    public void deeplyRemove(Long surveyId) {
        Preconditions.checkNotNull(surveyId);
        Survey survey = surveyDao.getSurveyWithBagAndQuestions(surveyId);
        surveyManager.deeplyRemove(survey);
    }
}
