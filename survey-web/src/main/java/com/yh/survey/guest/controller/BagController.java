package com.yh.survey.guest.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.condition.BagCondition;
import com.yh.survey.domain.pojo.Bag;
import com.yh.survey.domain.pojo.User;
import com.yh.survey.exceptions.RemoveBagFailedException;
import com.yh.survey.guest.interf.BagService;
import com.yh.survey.guest.interf.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Bag控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/guest/bag")
public class BagController {

    private static final Logger logger = LoggerFactory.getLogger(BagController.class);

    @Resource
    private BagService bagService;

    @Resource
    private QuestionService questionService;

    @RequestMapping("/toAdd/{surveyId}")
    public String toAdd(@PathVariable("surveyId") Long surveyId) {
        return "/guest/bag_add";
    }

    @RequestMapping("/saveBag")
    public String saveBag(Bag bag, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        bag.setBagNo(bag.getBagName());
        bag.setCreateUser(user.getUsername());
        bag.setCreateTime(new Date());
        bagService.saveBag(bag);
        return "redirect:/guest/survey/toDesign/" + bag.getSurveyId();
    }

    @RequestMapping("/toEdit/{bagId}")
    public String toEditUI(@PathVariable("bagId") Long bagId, Map<String, Object> map) {
        BagCondition condition = new BagCondition();
        condition.setId(bagId);
        Bag bag = bagService.getBagByCondition(condition);
        map.put("bag", bag);
        return "guest/bag_edit";
    }

    @RequestMapping("/updateBag")
    public String updateBag(Bag bag, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        Preconditions.checkNotNull(bag);
        bag.setUpdateUser(user.getUsername());
        bag.setUpdateTime(new Date());
        bagService.updateBag(bag);
        return "redirect:/guest/survey/toDesign/" + bag.getSurveyId();
    }

    @RequestMapping("/removeBag/{bagId}/{surveyId}")
    public String removeBag(@PathVariable("bagId") Long bagId, @PathVariable("surveyId") Long surveyId,HttpSession session) {
        Preconditions.checkNotNull(bagId);
        User user = (User)session.getAttribute("loginUser");
        Integer questionNum = questionService.queryQuestionNumByBagId(bagId);
        if (questionNum != null && questionNum > 0) {
            throw new RemoveBagFailedException(ExceptionMessage.REMOVE_BAG_FAILED);
        }
        BagCondition condition = new BagCondition();
        condition.setId(bagId);
        condition.setUpdateUser(user.getUsername());
        bagService.removeBag(condition);
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }

    @RequestMapping("/removeBagDeeply/{bagId}/{surveyId}")
    public String removeBagDeeply(@PathVariable("bagId") Long bagId,
                                  @PathVariable("surveyId") Long surveyId,
                                  HttpSession session) {
        try {
            User user = (User) session.getAttribute("loginUser");
            Preconditions.checkNotNull(bagId);
            BagCondition condition = new BagCondition();
            condition.setId(bagId);
            condition.setUpdateUser(user.getUsername());
            bagService.removeBagWithQuestions(condition);
        } catch (Exception e) {
            logger.error("BagController removeBagDeeply error:{}", e);
            throw new RemoveBagFailedException(ExceptionMessage.REMOVE_BAG_FAILED);
        }
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }


}