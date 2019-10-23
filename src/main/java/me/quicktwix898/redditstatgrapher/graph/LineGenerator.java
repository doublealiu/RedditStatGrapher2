package me.quicktwix898.redditstatgrapher.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.util.SortedMap;

public class LineGenerator extends ApplicationFrame implements GraphGenerator {
    private JFreeChart chart;

    public LineGenerator(String title, SortedMap<String, Integer> data) {
        super(WIN_TITLE);
        chart = ChartFactory.createLineChart(title, "Time", "Popularity",
                createDataset(data), PlotOrientation.VERTICAL, false, true, true);
    }

    private static CategoryDataset createDataset(SortedMap<String, Integer> map) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String defaultCategory = "Default";
        for(String name : map.keySet()) {
            dataset.addValue(new Double(map.get(name)), defaultCategory, name);
        }

        return dataset;
    }

    @Override
    public void openWindow() {
        setContentPane(new ChartPanel(chart));
        setSize(WIN_WIDTH, WIN_HEIGHT);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    @Override
    public void save(String filename) {
        try {
            File barChart = new File(filename);
            ChartUtils.saveChartAsJPEG(barChart, chart, WIN_WIDTH, WIN_HEIGHT);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e);
        }
    }
}
