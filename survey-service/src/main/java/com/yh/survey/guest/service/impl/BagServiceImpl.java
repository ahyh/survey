package com.yh.survey.guest.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.BagDao;
import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;
import com.yh.survey.guest.interf.BagService;
import org.springframework.stereotype.Service;

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
}
