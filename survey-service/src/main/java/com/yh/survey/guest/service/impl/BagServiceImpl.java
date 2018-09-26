package com.yh.survey.guest.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.BagDao;
import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;
import com.yh.survey.guest.interf.BagService;
import com.yh.survey.manager.BagManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 包裹服务实现类
 *
 * @author yanhuan
 */
@Service
public class BagServiceImpl implements BagService {

    @Resource
    private BagDao bagDao;

    @Resource
    private BagManager bagManager;

    /**
     * 级联查询出Bag对象
     *
     * @param bagId 包裹id
     * @return 包含Question集合的Bag对象
     */
    @Override
    public Bag getBagWithQuestions(Long bagId) {
        Preconditions.checkNotNull(bagId);
        return bagDao.getBagWithQuestions(bagId);
    }

    @Override
    public Bag getBagByCondition(BagCondition condition) {
        Preconditions.checkNotNull(condition);
        return bagDao.getBagByCondition(condition);
    }

    @Override
    @Transactional
    public Integer saveBag(Bag bag) {
        Preconditions.checkNotNull(bag);
        bagDao.insert(bag);
        bag.setBagOrder(Integer.valueOf(bag.getId().toString()));
        bag.setUpdateUser(bag.getCreateUser());
        return bagDao.update(bag);
    }

    @Override
    public Integer removeBag(BagCondition condition) {
        Preconditions.checkNotNull(condition);
        return bagDao.delete(condition);
    }

    @Override
    public Integer updateBag(Bag bag) {
        Preconditions.checkNotNull(bag);
        return bagDao.update(bag);
    }

    @Override
    public Integer queryBagNumBySurveyId(Long surveyId) {
        Preconditions.checkNotNull(surveyId);
        return bagDao.queryBagNumBySurveyId(surveyId);
    }

    @Override
    public Boolean removeBagWithQuestions(BagCondition condition) {
        Preconditions.checkNotNull(condition);
        Preconditions.checkArgument(condition.getId() != null, "bagId cannot null!");
        Preconditions.checkArgument(StringUtils.isNotBlank(condition.getUpdateUser()), "updateUser cannot null!");
        return bagManager.removeBagWithQuestions(condition);
    }
}