package com.yh.survey.manager.controller;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.Result;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.manager.service.ResService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Res控制器
 *
 * @author yanhuan
 */
@Controller
@RequestMapping("/manage/res")
public class ResController {

    private static final Logger logger = LoggerFactory.getLogger(ResController.class);

    @Resource
    private ResService resService;

    /**
     * 展示资源列表
     *
     * @return 跳转到资源列表页，并展示资源列表
     */
    @RequestMapping("/showList")
    public String showList(Map<String, Object> map) {
        List<Res> resList = resService.findResList();
        map.put("resList", resList);
        return "manager/res_list";
    }

    /**
     * 更新资源的公共标识属性
     *
     * @param resId   资源id
     * @param session session对象
     * @return 更新成功标识
     */
    @RequestMapping("/toggleStatus")
    @ResponseBody
    public Result toggleStatus(@RequestParam("resId") Long resId,
                               HttpSession session) {
        Result result = new Result();
        try {
            Preconditions.checkNotNull(resId);
            Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
            Byte publicStatus = resService.updatePublicStatus(resId, loginAdmin.getAdminName());
            result.setResultCode(1);
            result.setResultValue(publicStatus);
        } catch (Exception e) {
            logger.error("ResController toggleStatus error:{}", e);
            result.setResultCode(-1);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
