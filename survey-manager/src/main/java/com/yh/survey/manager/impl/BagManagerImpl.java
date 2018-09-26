package com.yh.survey.manager.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.BagDao;
import com.yh.survey.dao.QuestionDao;
import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.condition.QuestionCondition;
import com.yh.survey.manager.BagManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class BagManagerImpl implements BagManager {

    @Resource
    private BagDao bagDao;

    @Resource
    private QuestionDao questionDao;

    @Override
    @Transactional
    public Boolean removeBagWithQuestions(BagCondition condition) {
        Preconditions.checkNotNull(condition);
        Integer deleteBagNum = bagDao.delete(condition);
        if (deleteBagNum != 1) {
            throw new RuntimeException("删除包裹失败！");
        }
        QuestionCondition questionCondition = new QuestionCondition();
        questionCondition.setBagId(condition.getId());
        questionCondition.setUpdateUser(condition.getUpdateUser());
        Integer deleteQuestionNum = questionDao.deleteByCondition(questionCondition);
        if (deleteQuestionNum == null || deleteBagNum == 0) {
            throw new RuntimeException("删除包裹下的问题失败");
        }
        return true;
    }
}
