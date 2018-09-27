package com.yh.survey.manager.controller;

import com.github.pagehelper.PageInfo;
import com.yh.survey.domain.guest.condition.SurveyCondition;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.guest.interf.SurveyService;
import com.yh.survey.manager.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/statistics")
public class StatisticsController {

    @Resource
    private SurveyService surveyService;

    @Resource
    private StatisticsService statisticsService;

    @RequestMapping("/showAllAvailable")
    public String showAllAvailable(@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
                                   Map<String, Object> map) {
        SurveyCondition condition = new SurveyCondition();
        if (StringUtils.isBlank(pageNoStr)) {
            condition.setPageNum(1);
        } else {
            condition.setPageNum(Integer.valueOf(pageNoStr));
        }
        condition.setPageSize(5);
        condition.setSurveyStatus((byte) 1);
        PageInfo<Survey> surveyPage = surveyService.findSurveyPage(condition);
        map.put("page", surveyPage);
        return "manager/statistics_list";
    }


    @RequestMapping("/showSummary/{surveyId}")
    public String showSummary(@PathVariable("surveyId") Long surveyId, Map<String, Object> map) {
        Survey survey = surveyService.getSurveyWithBagAndQuestions(surveyId);
        map.put("survey", survey);
        return "manager/statistics_summary";
    }

//    @RequestMapping("/showChart")
//    public String showChart(){
//
//    }

    @RequestMapping("/showTextResult/{questionId}")
    public String showTextResult(@PathVariable("questionId")Long questionId,
                                 Map<String,Object> map){
        List<String> textResult = statisticsService.findTextResultList(questionId);
        map.put("textResult",textResult);
        return "manager/statistics_textResult";
    }
}
