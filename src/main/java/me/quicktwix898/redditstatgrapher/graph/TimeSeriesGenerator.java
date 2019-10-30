package me.quicktwix898.redditstatgrapher.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

//TODO untested

public class TimeSeriesGenerator extends ApplicationFrame implements GraphGenerator {
    private JFreeChart chart;
    public TimeSeriesGenerator(String title, Map<Timestamp, Integer> data) {
        super(WIN_TITLE);
        chart = ChartFactory.createTimeSeriesChart(title, "Time", "Frequency", createDataset(data),
                false, true, true);
    }

    private static XYDataset createDataset(Map<Timestamp, Integer> data) {
        final TimeSeries series = new TimeSeries("");
        for (Timestamp time : data.keySet()) {
            series.add(new Millisecond(time), data.get(time));
        }
        return new TimeSeriesCollection(series);
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
