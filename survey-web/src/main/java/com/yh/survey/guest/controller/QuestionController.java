package com.yh.survey.guest.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.pojo.Question;
import com.yh.survey.domain.pojo.User;
import com.yh.survey.guest.interf.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

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


}
