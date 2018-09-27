package com.yh.survey.guest.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.guest.condition.BagCondition;
import com.yh.survey.domain.guest.condition.SurveyCondition;
import com.yh.survey.domain.guest.pojo.Bag;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.exceptions.AdjustBagOrderException;
import com.yh.survey.exceptions.RemoveBagFailedException;
import com.yh.survey.guest.interf.BagService;
import com.yh.survey.guest.interf.QuestionService;
import com.yh.survey.guest.interf.SurveyService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
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

    @Resource
    private SurveyService surveyService;

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
    public String removeBag(@PathVariable("bagId") Long bagId, @PathVariable("surveyId") Long surveyId, HttpSession session) {
        Preconditions.checkNotNull(bagId);
        User user = (User) session.getAttribute("loginUser");
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

    @RequestMapping("/toAdjust/{surveyId}")
    public String toAdjust(@PathVariable("surveyId") Long surveyId, Map<String, Object> map) {
        Preconditions.checkNotNull(surveyId);
        List<Bag> bagList = bagService.findBagListBySurveyId(surveyId);
        map.put("bagList", bagList);
        return "guest/bag_adjust";
    }

    /**
     * 调整包裹顺序
     *
     * @param bagIdList    前台入参，包裹id集合
     * @param bagOrderList 前台入参，包裹bagOrder集合
     * @param surveyId     调查id，用于页面跳转使用
     * @param session      session对象
     * @return 跳转到调整好顺序后的调查设计页面
     */
    @RequestMapping("/doAdjust")
    public String doAdjust(@RequestParam("bagIdList") List<Long> bagIdList,
                           @RequestParam("bagOrderList") List<Integer> bagOrderList,
                           @RequestParam("surveyId") Long surveyId,
                           HttpSession session) {
        try {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(bagIdList), "bagIdList cannot null");
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(bagOrderList), "bagOrderList cannot null");
            Preconditions.checkArgument(bagIdList.size() == bagOrderList.size(), "bagIdList size should equals bagOrderList size");
            Preconditions.checkNotNull(surveyId);
            User user = (User) session.getAttribute("loginUser");
            List<Bag> bags = Lists.newArrayList();
            Bag bag;
            for (int i = 0; i < bagIdList.size(); i++) {
                bag = new Bag();
                bag.setId(bagIdList.get(i));
                bag.setBagOrder(bagOrderList.get(i));
                bag.setUpdateUser(user.getUsername());
                bags.add(bag);
            }
            bagService.doAdjust(bags);
        } catch (Exception e) {
            logger.error("BagController doAdjust error:{}", e);
            throw new AdjustBagOrderException(ExceptionMessage.ADJUST_BAR_ORDER_FAILED);
        }
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }

    @RequestMapping("/toMoveOrCopyPage/{bagId}/{surveyId}")
    public String toMoveOrCopyPage(@PathVariable("bagId") Long bagId,
                                   @PathVariable("surveyId") Long surveyId,
                                   @RequestParam(value = "pageNoStr", required = false) String pageNoStr,
                                   HttpSession session,
                                   Map<String, Object> map) {
        User user = (User) session.getAttribute("loginUser");
        String username = user.getUsername();
        SurveyCondition condition = new SurveyCondition();
        condition.setUsername(username);
        if (StringUtils.isBlank(pageNoStr)) {
            condition.setPageNum(1);
        } else {
            condition.setPageNum(Integer.valueOf(pageNoStr));
        }
        condition.setPageSize(5);
        condition.setSurveyStatus((byte) 0);
        PageInfo<Survey> surveyPage = surveyService.findSurveyPage(condition);
        map.put("page", surveyPage);
        return "guest/bag_moveOrCopyPage";
    }

    @RequestMapping("/copyToThisSurvey/{bagId}/{surveyId}")
    public String copyToThisSurvey(@PathVariable("bagId") Long bagId,
                                   @PathVariable("surveyId") Long surveyId,
                                   HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        bagService.updateRelationshipForCopy(bagId, surveyId, user.getUsername());
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }

    @RequestMapping("/moveToThisSurvey/{bagId}/{surveyId}")
    public String moveToThisSurvey(@PathVariable("bagId") Long bagId,
                                   @PathVariable("surveyId") Long surveyId,
                                   HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        bagService.updateRelationshipForMove(bagId, surveyId, user.getUsername());
        return "redirect:/guest/survey/toDesign/" + surveyId;
    }


}
