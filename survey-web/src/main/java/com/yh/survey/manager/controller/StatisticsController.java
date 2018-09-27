package com.yh.survey.manager.controller;

import com.github.pagehelper.PageInfo;
import com.yh.survey.consts.ExceptionMessage;
import com.yh.survey.domain.guest.condition.SurveyCondition;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.exceptions.ChartGetFailedException;
import com.yh.survey.exceptions.ExportExcelFailedException;
import com.yh.survey.guest.interf.SurveyService;
import com.yh.survey.manager.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/statistics")
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

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

    /**
     * 将问题的统计信息以图表的形式写在浏览器上
     *
     * @param questionId questionId
     * @param response   response对象
     */
    @RequestMapping("/showChart/{questionId}")
    public void showChart(@PathVariable("questionId") Long questionId,
                          HttpServletResponse response) {
        try {
            JFreeChart chart = statisticsService.getChart(questionId);
            ServletOutputStream outputStream = response.getOutputStream();
            ChartUtilities.writeChartAsJPEG(outputStream, chart, 800, 600);
        } catch (Exception e) {
            logger.error("StatisticsController showChart error:{}", e);
            throw new ChartGetFailedException(ExceptionMessage.CHART_GET_FAILED);
        }
    }

    /**
     * 以列表的形式展示简答题的答案
     *
     * @param questionId questionId
     * @param map        map域对象
     * @return 简答题答案展示页面
     */
    @RequestMapping("/showTextResult/{questionId}")
    public String showTextResult(@PathVariable("questionId") Long questionId,
                                 Map<String, Object> map) {
        List<String> textResult = statisticsService.findTextResultList(questionId);
        map.put("textResult", textResult);
        return "manager/statistics_textResult";
    }

    @RequestMapping("/exportExcel/{surveyId}")
    public void exportExcel(@PathVariable("surveyId") Long surveyId, HttpServletResponse response) {
        try {
            SXSSFWorkbook workbook = statisticsService.getWorkbook(surveyId);
            response.setContentType("application/vnd.ms-excel");
            String fileName = System.nanoTime()+".xls";
            response.setHeader("Content-Disposition", "attachment;filename=".concat(new String(fileName.getBytes("GBK"), "iso8859-1")));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error("StatisticsController exportExcel error:{}", e);
            throw new ExportExcelFailedException(ExceptionMessage.EXPORT_EXCEL_FAILED);
        }
    }
}
