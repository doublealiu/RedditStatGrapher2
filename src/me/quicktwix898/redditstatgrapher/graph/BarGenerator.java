package me.quicktwix898.redditstatgrapher.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BarGenerator extends ApplicationFrame implements GraphGenerator {
    private JFreeChart chart;
    public BarGenerator(String title, Map<String, Integer> data) {
        super(WIN_TITLE);
        chart = ChartFactory.createBarChart(title, "", "Occurances",
                createDataset(data));
    }

    private static CategoryDataset createDataset(Map<String, Integer> map) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String defaultCategory = "Default";
        for(String name : map.keySet()) {
            dataset.setValue(new Double(map.get(name)), defaultCategory, name);
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

    //DEMO
    public static void main(String[] args) {
        HashMap<String, Integer> test = new HashMap<String, Integer>();
        test.put("Test", 100);
        test.put("2", 200);
        test.put("Aaron", 50);
        test.put("Me", 300);
        test.put("Yeet", 90);

        BarGenerator demo = new BarGenerator("Fancy Analysis", test);
        demo.openWindow();
    }
}
