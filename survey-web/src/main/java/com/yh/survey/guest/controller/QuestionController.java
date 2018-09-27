package com.yh.survey.guest.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.guest.condition.QuestionCondition;
import com.yh.survey.domain.guest.pojo.Question;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.guest.interf.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Question控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/guest/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 跳转到问题创建页面
     *
     * @param bagId    包裹id
     * @param surveyId 调查id
     * @return 跳转到问题创建页面
     */
    @RequestMapping("/toAdd/{bagId}/{surveyId}")
    public String toAdd(@PathVariable("bagId") Long bagId, @PathVariable("surveyId") Long surveyId) {
        return "/guest/question_add";
    }

    @RequestMapping("/saveQuestion")
    public String saveQuestion(Question question, @RequestParam("surveyId") Long surveyId, HttpSession session) {
        Preconditions.checkNotNull(question);
        Preconditions.checkNotNull(surveyId);
        User user = (User) session.getAttribute("loginUser");
        question.setQuestionNo(question.getQuestionName());
        question.setCreateUser(user.getUsername());
        question.setCreateTime(new Date());
        questionService.saveQuestion(question);
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }

    @RequestMapping("/removeQuestion/{questionId}/{surveyId}")
    public String removeQuestion(@PathVariable("questionId") Long questionId, @PathVariable("surveyId") Long surveyId) {
        questionService.removeQuestion(questionId);
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }

    /**
     * 去更新问题页面
     *
     * @param questionId question主键
     * @param surveyId   survey主键
     * @param map        域对象
     * @return 跳转到更新问题页面
     */
    @RequestMapping("/toEdit/{questionId}/{surveyId}")
    public String toEditUI(@PathVariable("questionId") Long questionId,
                           @PathVariable("surveyId") Long surveyId,
                           Map<String, Object> map) {
        Preconditions.checkNotNull(questionId);
        QuestionCondition condition = new QuestionCondition();
        condition.setId(questionId);
        Question question = questionService.getQuestionByCondition(condition);
        map.put("question", question);
        Map<String, String> radioMap = new HashMap<>(3);
        radioMap.put("0", "单选题");
        radioMap.put("1", "多选题");
        radioMap.put("2", "简答题");
        map.put("radioMap", radioMap);
        return "guest/question_edit";
    }

    /**
     * 更新问题
     *
     * @param question 前台入参
     * @param surveyId 调查id
     * @param session  HttpSession对象
     * @return 更新问题后，跳转到调查设计页面
     */
    @RequestMapping("/updateQuestion")
    public String updateQuestion(Question question, @RequestParam("surveyId") Long surveyId, HttpSession session) {
        Preconditions.checkNotNull(question);
        User user = (User) session.getAttribute("loginUser");
        question.setUpdateUser(user.getUsername());
        question.setUpdateTime(new Date());
        questionService.updateQuestion(question);
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }


}
