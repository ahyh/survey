package com.yh.survey.guest.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.condition.SurveyCondition;
import com.yh.survey.domain.pojo.Bag;
import com.yh.survey.domain.pojo.Survey;
import com.yh.survey.domain.pojo.User;
import com.yh.survey.enums.SurveyStatusEnum;
import com.yh.survey.exceptions.EntrySurveyFailedException;
import com.yh.survey.exceptions.FinishSurveyException;
import com.yh.survey.guest.interf.EngageService;
import com.yh.survey.guest.interf.SurveyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/guest/engage")
public class EngageController {

    private static final Logger logger = LoggerFactory.getLogger(EngageController.class);

    @Resource
    private EngageService engageService;

    @Resource
    private SurveyService surveyService;


    @RequestMapping("/showAllAvailable")
    public String showAllAvailable(@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
                                   Map<String, Object> map) {
        SurveyCondition condition = new SurveyCondition();
        if (StringUtils.isBlank(pageNoStr)) {
            condition.setPageNum(1);
        } else {
            condition.setPageNum(Integer.valueOf(pageNoStr));
        }
        condition.setPageSize(2);
        condition.setSurveyStatus(SurveyStatusEnum.COMPLETED.getKey());
        PageInfo<Survey> surveyPage = surveyService.findSurveyPage(condition);
        map.put("page", surveyPage);
        return "guest/engage_allAvailable";
    }

    @RequestMapping("/entry/{surveyId}")
    public String entry(@PathVariable("surveyId") Long surveyId,
                        HttpSession session,
                        Map<String, Object> map) {
        try {
            Preconditions.checkNotNull(surveyId);
            Survey survey = surveyService.getSurveyWithBagAndQuestions(surveyId);
            List<Bag> bagList = Lists.newArrayList(survey.getBagSet());
            session.setAttribute("currentSurvey", survey);
            session.setAttribute("bagList", bagList);
            session.setAttribute("lastIndex", survey.getBagSet().size() - 1);
            Map<Long, Map<String, String[]>> allBagMap = new HashMap<>();
            session.setAttribute("allBagMap", allBagMap);
            int currentIndex = 0;
            Bag bag = bagList.get(currentIndex);
            map.put("currentBag", bag);
            map.put("currentIndex", currentIndex);
            return "guest/engage_engage";
        } catch (Exception e) {
            logger.error("EngageController entry error:{}", e);
            throw new EntrySurveyFailedException(ExceptionMessage.ENTRY_SURVEY_FAILED);
        }
    }

    @RequestMapping("/engage")
    public String engage(HttpServletRequest request,
                         HttpSession session,
                         @RequestParam("currentIndex") Integer currentIndex,
                         @RequestParam("bagId") Long bagId) {
        //1.从request对象中获取请求参数Map对象
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<Long, Map<String, String[]>> allBagMap = (Map<Long, Map<String, String[]>>) session.getAttribute("allBagMap");
        //为了避免合并答案时后面的请求覆盖前面请求的数据，每次都创建新的Map对象保存请求参数
        Map<String, String[]> newParamMap = new HashMap<>(paramMap);
        allBagMap.put(bagId, newParamMap);
        Set<Map.Entry<Long, Map<String, String[]>>> entrySet = allBagMap.entrySet();
        for (Map.Entry<Long, Map<String, String[]>> entry : entrySet) {
            Long bagIdCurrent = entry.getKey();
            Map<String, String[]> currentParamMap = entry.getValue();
            Set<Map.Entry<String, String[]>> entrySet2 = currentParamMap.entrySet();
            for (Map.Entry<String, String[]> entry2 : entrySet2) {
                String paramName = entry2.getKey();
                String[] paramValue = entry2.getValue();
                List<String> paramValueList = Arrays.asList(paramValue);
            }
        }

        //2.检查paramMap中是否存在提交按钮对应的键
        if (paramMap.containsKey("submit_prev")) {
            int prevIndex = currentIndex - 1;
            List<Bag> bagList = (List<Bag>) session.getAttribute("bagList");
            Bag prevBag = bagList.get(prevIndex);
            request.setAttribute("currentBag", prevBag);
            request.setAttribute("currentIndex", prevIndex);
            return "guest/engage_engage";
        }

        if (paramMap.containsKey("submit_next")) {
            int nextIndex = currentIndex + 1;
            List<Bag> bagList = (List<Bag>) session.getAttribute("bagList");
            Bag nextBag = bagList.get(nextIndex);
            request.setAttribute("currentBag", nextBag);
            request.setAttribute("currentIndex", nextIndex);
            return "guest/engage_engage";
        }

        if (paramMap.containsKey("submit_quit")) {
            session.removeAttribute("allBagMap");
            session.removeAttribute("bagList");
            session.removeAttribute("currentSurvey");
            session.removeAttribute("lastIndex");
            return "redirect:/index.jsp";
        }

        try{
            if (paramMap.containsKey("submit_done")) {
                Survey survey = (Survey) session.getAttribute("currentSurvey");
                Long surveyId = survey.getId();
                User user = (User) session.getAttribute("loginUser");
                engageService.saveByParse(allBagMap, surveyId, user.getUsername());
                session.removeAttribute("allBagMap");
                session.removeAttribute("bagList");
                session.removeAttribute("currentSurvey");
                session.removeAttribute("lastIndex");
                return "redirect:/index.jsp";
            }
        }catch (Exception e){
            logger.error("EngageController finish survey error:{}",e);
            throw new FinishSurveyException(ExceptionMessage.FINISH_SURVEY_FAILED);
        }
        return "";
    }
}
