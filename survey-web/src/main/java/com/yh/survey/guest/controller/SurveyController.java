package com.yh.survey.guest.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.guest.condition.SurveyCondition;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.enums.SurveyStatusEnum;
import com.yh.survey.exceptions.*;
import com.yh.survey.guest.interf.BagService;
import com.yh.survey.guest.interf.SurveyService;
import com.yh.survey.utils.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Survey控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/guest/survey")
public class SurveyController {

    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

    @Resource
    private SurveyService surveyService;

    @Resource
    private BagService bagService;

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "guest/survey_add";
    }

    @RequestMapping("/saveSurvey")
    public String saveSurvey(Survey survey, HttpSession session, @RequestParam("logoFile") MultipartFile logoFile) throws IOException {
        //1.判断用户是否真的上传了文件
        boolean empty = logoFile.isEmpty();
        if (!empty) {
            long size = logoFile.getSize();
            if (size > 1024000) {
                throw new FileTooLargeException(ExceptionMessage.FILE_TOO_LARGE);
            }
            //ii.文件类型验证
            String contentType = logoFile.getContentType();
            if (!contentType.startsWith("image/")) {
                throw new FileTypeInvalidException(ExceptionMessage.FILE_TYPE_INVALIDE);
            }
            //2.通过Session对象获取ServletContext
            ServletContext servletContext = session.getServletContext();
            //3.声明虚拟路径
            String virtualPath = "/surveyLogos";
            //4.根据虚拟路径生成真实路径
            String realPath = servletContext.getRealPath(virtualPath);
            //5.获取上传文件的输入流
            InputStream inputStream = logoFile.getInputStream();
            //6.压缩图片
            String logoPath = ImageUtil.resizeImages(inputStream, realPath);
            //7.使用这个logoPath值设置survey对象中的logoPath属性
            survey.setLogoPath(logoPath);
        }
        User user = (User) session.getAttribute("loginUser");
        survey.setSurveyNo(UUID.randomUUID().toString());
        survey.setUsername(user.getUsername());
        survey.setCreateTime(new Date());
        survey.setCreateUser(user.getUsername());
        survey.setSurveyStatus((byte) 0);
        surveyService.insert(survey);
        return "redirect:/guest/survey/showMyUncompleted";
    }

    @RequestMapping("/showMyUncompleted")
    public String showMyUncompleted(HttpSession session,
                                    @RequestParam(value = "pageNoStr", required = false) String pageNoStr,
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
        return "guest/survey_uncompleted";
    }

    /**
     * 删除调查，如果调查下仍有包裹则不能删除
     *
     * @param surveyId survey的id
     * @param pageNum  页码
     * @return 重定向到未完成的调查页面
     */
    @RequestMapping("/removeSurvey/{surveyId}/{pageNum}")
    public String removeSurvey(@PathVariable("surveyId") Long surveyId, @PathVariable("pageNum") Integer pageNum) {
        Preconditions.checkNotNull(surveyId);
        Integer bagNum = bagService.queryBagNumBySurveyId(surveyId);
        if (bagNum != null && bagNum > 0) {
            throw new RemoveSurveyFailedException(ExceptionMessage.REMOVE_SURVEY_FAILED);
        }
        surveyService.removeSurvey(surveyId);
        return "redirect:/guest/survey/showMyUncompleted?pageNoStr=" + pageNum;
    }

    /**
     * 深度删除调查，同时删除调查下所有的包裹及包裹中的所有问题
     *
     * @param surveyId 调查id
     * @param pageNum  分页页码
     * @return 深度删除后跳转到调查展示页面
     */
    @RequestMapping("/deeplyRemove/{surveyId}/{pageNum}")
    public String deeplyRemove(@PathVariable("surveyId") Long surveyId,
                               @PathVariable("pageNum") Integer pageNum) {
        try {
            Preconditions.checkNotNull(surveyId);
            surveyService.deeplyRemove(surveyId);
            return "redirect:/guest/survey/showMyUncompleted?pageNoStr=" + pageNum;
        } catch (Exception e) {
            logger.error("SurveyController deeplyRemove error:{}", e);
            throw new DeeplyRemoveSurveyFailedException(ExceptionMessage.DEEPLY_REMOVE_SURVEY_FAILED);
        }
    }

    @RequestMapping("/toEdit/{id}/{pageNum}")
    public String toEdit(@PathVariable("id") Long id, @PathVariable("pageNum") Integer pageNum, Map<String, Object> map) {
        SurveyCondition condition = new SurveyCondition();
        condition.setId(id);
        Survey survey = surveyService.getSurveyByCondition(condition);
        map.put("survey", survey);
        return "guest/survey_edit";
    }

    @RequestMapping("/updateSurvey")
    public String updateSurvey(Survey survey, @RequestParam("logoFile") MultipartFile logoFile,
                               HttpSession session, HttpServletRequest request, @RequestParam("pageNum") Integer pageNum) throws IOException {
        //1.判断用户是否真的上传了文件
        boolean empty = logoFile.isEmpty();
        if (!empty) {
            //i.文件大小验证
            long size = logoFile.getSize();
            if (size > 1024000) {
                request.setAttribute("survey", survey);
                throw new UpdateFileTooLargeException(ExceptionMessage.FILE_TOO_LARGE);
            }
            //ii.文件类型验证
            String contentType = logoFile.getContentType();
            if (!contentType.startsWith("image/")) {
                request.setAttribute("survey", survey);
                throw new UpdateFileTypeInvalidException(ExceptionMessage.FILE_TYPE_INVALIDE);
            }
            //2.通过Session对象获取ServletContext
            ServletContext servletContext = session.getServletContext();
            //3.声明虚拟路径
            String virtualPath = "/surveyLogos";
            //4.根据虚拟路径生成真实路径
            String realPath = servletContext.getRealPath(virtualPath);
            //5.获取上传文件的输入流
            InputStream inputStream = logoFile.getInputStream();
            //6.压缩图片
            String logoPath = ImageUtil.resizeImages(inputStream, realPath);
            //7.使用这个logoPath值设置survey对象中的logoPath属性
            survey.setLogoPath(logoPath);
        }
        survey.setUpdateUser(((User) session.getAttribute("loginUser")).getUsername());
        surveyService.updateSurvey(survey);
        return "redirect:/guest/survey/showMyUncompleted?pageNoStr=" + pageNum;
    }

    /**
     * 跳转到调查设计页面
     *
     * @param surveyId survey表的id
     * @param map      域对象
     * @return 跳转到调查设计页面
     */
    @RequestMapping("/toDesign/{surveyId}")
    public String toDesign(@PathVariable("surveyId") Long surveyId, Map<String, Object> map) {
        Survey survey = surveyService.getSurveyWithBagAndQuestions(surveyId);
        map.put("survey", survey);
        return "guest/survey_design";
    }

    /**
     * 更新调查的状态为完成
     *
     * @param surveyId survey表的主键
     * @param session  session对象
     * @return 更新完成后返回至首页
     */
    @RequestMapping("/complete/{surveyId}")
    public String complete(@PathVariable("surveyId") Long surveyId, HttpSession session) {
        try {
            User user = (User) session.getAttribute("loginUser");
            Survey survey = new Survey();
            survey.setId(surveyId);
            survey.setUpdateUser(user.getUsername());
            survey.setSurveyStatus(SurveyStatusEnum.COMPLETED.getKey());
            surveyService.updateSurveyComplete(survey);
            return "redirect:/index.jsp";
        } catch (Exception e) {
            logger.error("SurveyController complete error:{}", e);
            throw new CompleteSurveyException(ExceptionMessage.COMPLETE_SURVEY_FAILED);
        }
    }
}
