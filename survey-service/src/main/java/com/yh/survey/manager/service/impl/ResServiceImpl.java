package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.ResDao;
import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.manager.service.ResService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ResServiceImpl
 *
 * @author yanhuan
 */
@Service
public class ResServiceImpl implements ResService {

    @Resource
    private ResDao resDao;

    @Override
    public Integer saveRes(Res res) {
        Preconditions.checkNotNull(res);
        return resDao.insert(res);
    }

    @Override
    public Res getResByCondition(ResCondition condition) {
        Preconditions.checkNotNull(condition);
        return resDao.getResByCondition(condition);
    }

    @Override
    public Integer getMaxResCode(Integer maxPos) {
        Preconditions.checkNotNull(maxPos);
        return resDao.getMaxResCode(maxPos);
    }

    @Override
    public Integer getMaxResPos() {
        return resDao.getMaxResPos();
    }

    @Override
    public List<Res> findResList() {
        return resDao.findResList();
    }

    @Override
    public Byte updatePublicStatus(Long resId, String updateUser) {
        Preconditions.checkNotNull(resId);
        resDao.updatePublicStatus(resId, updateUser);
        ResCondition condition = new ResCondition();
        condition.setId(resId);
        Res res = resDao.getResByCondition(condition);
        return res.getPublicStatus();
    }

    @Override
    public Integer batchDelete(List<Long> resIdList,String updateUser) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(resIdList), "resIdList cannot empty");
        return resDao.batchDelete(resIdList,updateUser);
    }
}
