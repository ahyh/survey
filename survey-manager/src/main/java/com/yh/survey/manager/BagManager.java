package com.yh.survey.manager;

import com.yh.survey.domain.guest.condition.BagCondition;
import com.yh.survey.domain.guest.pojo.Bag;

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

    /**
     * 插入bag记录，同时插入question记录
     *
     * @param bag 包含questionSet的bag对象
     * @return question表写数据的行数
     */
    Integer insertBagWithQuestions(Bag bag);

    Integer updateBagWithQuestions(Bag bag);
}
