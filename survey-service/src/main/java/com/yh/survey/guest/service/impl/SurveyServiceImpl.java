package com.yh.survey.guest.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yh.survey.dao.SurveyDao;
import com.yh.survey.domain.condition.SurveyCondition;
import com.yh.survey.domain.pojo.Bag;
import com.yh.survey.domain.pojo.Question;
import com.yh.survey.domain.pojo.Survey;
import com.yh.survey.guest.interf.SurveyService;
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
        LinkedHashSet<Bag> bagSet = survey.getBagSet();
        for (Bag bag : bagSet) {
            LinkedHashSet<Question> questionSet = bag.getQuestionSet();
            if (CollectionUtils.isNotEmpty(questionSet) && questionSet.size() == 1) {
                for (Question question : questionSet) {
                    if (StringUtils.isBlank(question.getQuestionName()) || question.getIsDelete().equals(new Byte("0"))) {
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
        return survey;
    }
}
