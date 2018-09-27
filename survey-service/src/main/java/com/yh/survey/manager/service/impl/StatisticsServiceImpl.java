package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.yh.survey.dao.AnswerDao;
import com.yh.survey.dao.QuestionDao;
import com.yh.survey.domain.guest.condition.AnswerCondition;
import com.yh.survey.domain.guest.condition.QuestionCondition;
import com.yh.survey.domain.guest.pojo.Answer;
import com.yh.survey.domain.guest.pojo.Bag;
import com.yh.survey.domain.guest.pojo.Question;
import com.yh.survey.domain.guest.pojo.Survey;
import com.yh.survey.guest.interf.SurveyService;
import com.yh.survey.manager.service.StatisticsService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private AnswerDao answerDao;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private SurveyService surveyService;

    @Override
    public List<String> findTextResultList(Long questionId) {
        Preconditions.checkNotNull(questionId);
        return answerDao.findAnswerContentListByQuestionId(questionId);
    }

    @Override
    public JFreeChart getChart(Long questionId) {
        Preconditions.checkNotNull(questionId);
        //1-根据questionId获取Question对象
        QuestionCondition questionCondition = new QuestionCondition();
        questionCondition.setId(questionId);
        Question question = questionDao.getQuestionByCondition(questionCondition);
        Preconditions.checkNotNull(question);
        //2-根据questionId查询问题被参与的次数
        Integer questionEngageCount = answerDao.getQuestionEngageCount(questionId);
        //3-从问题对象中获取问题名称
        String questionName = question.getQuestionName();
        //4-从问题对象中获取选项数组
        String[] optionsArray = question.getOptionsArray();
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < optionsArray.length; i++) {
            //查询当前选项被参与次数
            Integer optionEngageCount = answerDao.getOptionEngageCount(questionId, i);
            dataset.setValue(optionsArray[i], optionEngageCount);
        }
        String title = questionName + questionEngageCount + "次参与";
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset);
        chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 50));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 30));
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setLabelFont(new Font("宋体", Font.PLAIN, 20));
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));
        piePlot.setForegroundAlpha(0.6f);
        return chart;
    }

    /**
     * 根据surveyId查询答案的统计信息用于Excel文件到处
     *
     * @param surveyId 调查主键
     * @return workbook
     */
    @Override
    public SXSSFWorkbook getWorkbook(Long surveyId) {
        Preconditions.checkNotNull(surveyId);
        Survey survey = surveyService.getSurveyWithBagAndQuestions(surveyId);
        String surveyName = survey.getSurveyName();
        Set<Bag> bagSet = survey.getBagSet();
        List<Question> questionList = Lists.newArrayList();
        for (Bag bag : bagSet) {
            Set<Question> questionSet = bag.getQuestionSet();
            questionList.addAll(questionSet);
        }
        //查询surveyId被参与次数
        Integer surveyEngageCount = answerDao.getSurveyEngageCount(surveyId);
        //根据surveyId查询所有答案
        AnswerCondition answerCondition = new AnswerCondition();
        answerCondition.setSurveyId(surveyId);
        List<Answer> answerList = answerDao.findAnswerListByCondition(answerCondition);
        Map<String, Map<Long, String>> bigMap = generateBigMap(answerList);

        //三、根据准备好的数据创建工作表
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        String title = surveyName + surveyEngageCount + "次参与";
        SXSSFSheet sheet = workbook.createSheet(title);
        //第一行的内容是所有问题名称
        SXSSFRow row = sheet.createRow(0);
        for (int i = 0; i < questionList.size(); i++) {
            String questionName = questionList.get(i).getQuestionName();
            SXSSFCell cell = row.createCell(i);
            cell.setCellValue(questionName);
        }
        //生成后续每一行
        int rowIndex = 1;
        Set<Map.Entry<String, Map<Long, String>>> entrySet = bigMap.entrySet();
        for (Map.Entry<String, Map<Long, String>> entry : entrySet) {
            Map<Long, String> smallMap = entry.getValue();
            SXSSFRow answerRow = sheet.createRow(rowIndex);
            //※注意：用户参与调查时如果有问题没有提供答案，那么smallMap的长度会小于questionList的长度
            //所以这里遍历questionList才是完整的
            for (int i = 0; i < questionList.size(); i++) {
                //以当前循环变量的值为索引创建当前单元格
                SXSSFCell answerCell = answerRow.createCell(i);
                //取出对应的Question对象
                Question question = questionList.get(i);
                //取出当前Question对象的questionId
                Long questionId = question.getId();
                //以questionId为键从smallMap中获取对应的答案内容
                String content = smallMap.get(questionId);
                //将答案内容设置到当前单元格中
                answerCell.setCellValue(content);
            }
            rowIndex++;
        }
        return workbook;
    }

    /**
     * 根据answerList获取答案大Map
     */
    public Map<String, Map<Long, String>> generateBigMap(List<Answer> answerList) {
        //1.创建空的bigMap
        Map<String, Map<Long, String>> bigMap = new HashMap<>();
        //2.遍历answerList
        for (Answer answer : answerList) {
            //3.从每一个Answer对象中取出需要用到的数据
            String answerContent = answer.getAnswerContent();
            String answerUuid = answer.getAnswerUuid();
            Long questionId = answer.getQuestionId();
            //4.尝试从bigMap中获取当前Answer对象对应的smallMap
            Map<Long, String> smallMap = bigMap.get(answerUuid);
            //5.如果smallMap为null，说明以前没有保存过，那么创建新的并放入bigMap
            if (smallMap == null) {
                smallMap = new HashMap<>();
                bigMap.put(answerUuid, smallMap);
            }
            //6.将数据保存到smallMap
            smallMap.put(questionId, answerContent);
        }
        return bigMap;
    }
}
