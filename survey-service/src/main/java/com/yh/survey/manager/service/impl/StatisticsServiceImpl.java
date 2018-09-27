package com.yh.survey.manager.service.impl;

import com.google.common.base.Preconditions;
import com.yh.survey.dao.AnswerDao;
import com.yh.survey.dao.QuestionDao;
import com.yh.survey.domain.guest.condition.QuestionCondition;
import com.yh.survey.domain.guest.pojo.Question;
import com.yh.survey.manager.service.StatisticsService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private AnswerDao answerDao;

    @Resource
    private QuestionDao questionDao;

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
}
