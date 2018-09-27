package com.yh.survey.test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

import java.awt.*;
import java.io.File;

public class JFreeChartTest {

    @Test
    public void testJFreeChart() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("APPLE", 15);
        dataset.setValue("华为", 20);
        dataset.setValue("小米", 15);
        dataset.setValue("魅族", 10);
        dataset.setValue("锤子", 10);
        dataset.setValue("360", 10);
        dataset.setValue("中兴", 10);
        dataset.setValue("一加", 10);

        JFreeChart chart = ChartFactory.createPieChart3D("手机市场占有率", dataset);

        //调整字体
        chart.getTitle().setFont(new Font("宋体", Font.ITALIC, 50));
        chart.getLegend().setItemFont(new Font("宋体", Font.ITALIC, 30));
        Plot plot = chart.getPlot();
        PiePlot piePlot = (PiePlot) plot;
        piePlot.setLabelFont(new Font("宋体", Font.ITALIC, 30));

        /**
         * 设置标签显示的格式
         * 0：当前标签文字信息
         * 1：当前标签本身的数值
         * 2：当前标签数值在总数中的百分比
         * 3：总数
         */
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));
        File file = new File("E:/chart.jpg");

        ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);

    }
}
