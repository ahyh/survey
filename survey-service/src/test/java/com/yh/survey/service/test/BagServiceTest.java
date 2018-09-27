package com.yh.survey.service.test;

import com.yh.survey.domain.guest.condition.BagCondition;
import com.yh.survey.domain.guest.pojo.Bag;
import com.yh.survey.guest.interf.BagService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class BagServiceTest extends BaseTest {

    @Resource
    private BagService bagService;

    @Test
    public void testGetBagWithQuestions() {
        Long bagId = 1L;
        Bag bag = bagService.getBagWithQuestions(bagId);
        Assert.assertTrue(bag != null && CollectionUtils.isNotEmpty(bag.getQuestionSet()));
    }

    @Test
    public void testGetBagByCondition(){
        BagCondition condition = new BagCondition();
        condition.setId(1L);
        Bag bagByCondition = bagService.getBagByCondition(condition);
        System.out.print(bagByCondition.getBagName());
    }

    @Test
    public void testGetBagNumBySurveyId(){
        Long surveyId = 1L;
        Integer num = bagService.queryBagNumBySurveyId(surveyId);
        System.out.println(num);
    }
}
