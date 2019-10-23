package me.quicktwix898.redditstatgrapher.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.util.Map;

public class PieGenerator extends ApplicationFrame implements GraphGenerator {
    private JFreeChart chart;

    public PieGenerator(String title, Map<String, Integer> data) {
        super(WIN_TITLE);
        chart = ChartFactory.createPieChart(title, createDataset(data),
                false, true, false);
    }

    private static PieDataset createDataset(Map<String, Integer> map) {
        Map<String, Integer> sorted = GraphGenerator.sortByValue(map);
        DefaultPieDataset dataset = new DefaultPieDataset();
        int i=0;
        for (String name : sorted.keySet()) {
            if(i<MAX_CATEGORY_ENTRIES) {
                dataset.setValue(name, new Double(map.get(name)));
            }
            i++;
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
            File pieChart = new File(filename);
            ChartUtils.saveChartAsJPEG(pieChart, chart, WIN_WIDTH, WIN_HEIGHT);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e);
        }
    }
}
