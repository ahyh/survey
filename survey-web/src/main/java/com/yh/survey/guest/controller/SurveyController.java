package com.yh.survey.guest.controller;

import com.github.pagehelper.PageInfo;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.condition.SurveyCondition;
import com.yh.survey.domain.pojo.Survey;
import com.yh.survey.domain.pojo.User;
import com.yh.survey.exceptions.FileTooLargeException;
import com.yh.survey.exceptions.FileTypeInvalidException;
import com.yh.survey.guest.interf.SurveyService;
import com.yh.survey.utils.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
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

    @Resource
    private SurveyService surveyService;

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
        //todo 后续考虑从redis中获取
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
        condition.setPageSize(2);
        condition.setSurveyStatus((byte) 0);
        PageInfo<Survey> surveyPage = surveyService.findSurveyPage(condition);
        map.put("page", surveyPage);
        return "guest/survey_uncompleted";
    }

    /**
     * 删除调查
     *
     * @param id      survey的id
     * @param pageNum 页码
     * @return 重定向到未完成的调查页面
     */
    @RequestMapping("/removeSurvey/{id}/{pageNum}")
    public String removeSurvey(@PathVariable("id") Long id, @PathVariable("pageNum") Integer pageNum) {
        surveyService.removeSurvey(id);
        return "redirect:/guest/survey/showMyUncompleted?pageNoStr=" + pageNum;
    }
}
