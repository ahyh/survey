package com.yh.survey.service.test;

import com.yh.survey.guest.interf.QuestionService;
import org.junit.Test;

import javax.annotation.Resource;

public class QuestionServiceTest extends BaseTest {

    @Resource
    private QuestionService questionService;

    @Test
    public void testGetQuestionNumByBagId() {
        Long bagId = 1L;
        Integer sum = questionService.queryQuestionNumByBagId(bagId);
        System.out.print(sum);
    }


}
