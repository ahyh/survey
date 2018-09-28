package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.ResCondition;
import com.yh.survey.domain.manager.pojo.Res;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ResDao
 *
 * @author yanhuan
 */
public interface ResDao {

    Integer insert(Res res);

    Integer update(Res res);

    Integer delete(Long id);

    Res getResByCondition(ResCondition condition);

    Integer getMaxResPos();

    Integer getMaxResCode(Integer maxPos);

    List<Res> findResList();

    Integer updatePublicStatus(@Param("resId") Long resId, @Param("updateUser") String updateUser);
}
