package me.quicktwix898.redditstatgrapher.graph;

import jdk.jshell.spi.ExecutionControl;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

public class HistogramGenerator extends ApplicationFrame implements GraphGenerator {
    private JFreeChart chart;

    public HistogramGenerator(String title, Double[] data) throws ExecutionControl.NotImplementedException {
        // no use for this because no distinction from bar chart.
        // analysing, grouping unix times should not be done by this class
        throw new ExecutionControl.NotImplementedException("");
    }
}
