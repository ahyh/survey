package com.yh.survey.manager;

import com.yh.survey.domain.condition.BagCondition;

public interface BagManager {

    /**
     * 删除包裹及包裹下所有问题
     *
     * @param condition 包含bagId，updateUser信息
     * @return 是否成功删除
     */
    Boolean removeBagWithQuestions(BagCondition condition);
}
