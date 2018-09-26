package com.yh.survey.manager;

import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;

import java.util.List;

public interface BagManager {

    /**
     * 删除包裹及包裹下所有问题
     *
     * @param condition 包含bagId，updateUser信息
     * @return 是否成功删除
     */
    Boolean removeBagWithQuestions(BagCondition condition);

    /**
     * 调整包裹顺序
     *
     * @param bagList 包裹集合，包裹id，bagOrder，updateUser信息
     * @return 更改的行数
     */
    Integer adjust(List<Bag> bagList);
}
