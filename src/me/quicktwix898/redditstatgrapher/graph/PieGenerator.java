package me.quicktwix898.redditstatgrapher.graph;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.HashMap;
import java.util.Map;

class PieGenerator extends ApplicationFrame {
    private static HashMap<String, Integer> test = new HashMap<String, Integer>();
    private JFreeChart chart;

    public PieGenerator(String title, Map<String, Integer> data) {
        super(title);
        chart = ChartFactory.createPieChart(title, createDataset(data),
                false, true, false);
    }

    public void open() {
        setContentPane(new ChartPanel(chart));
    }

    private static PieDataset createDataset(Map<String, Integer> map) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String name : map.keySet()) {
            dataset.setValue(name, new Double(map.get(name)));
        }
        return dataset;
    }

    public static void main(String[] args) {
        PieGenerator demo = new PieGenerator("RedditStat");
        demo.setSize(560 , 367);
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
