package com.yh.survey.dao;

import com.yh.survey.domain.manager.condition.AuthCondition;
import com.yh.survey.domain.manager.pojo.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AuthDao
 *
 * @author yanhuan
 */
public interface AuthDao {

    Integer insert(Auth auth);

    Integer update(Auth auth);

    Integer delete(Auth id);

    Auth getAuthByCondition(AuthCondition condition);

    List<Auth> findAuthList();

    Integer batchDelete(@Param("list") List<Long> authIdList, @Param("updateUser") String updateUser);
}
