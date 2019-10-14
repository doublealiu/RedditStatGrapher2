package me.quicktwix898.redditstatgrapher.graph;


import org.jfree.chart.JFreeChart;

public interface GraphGenerator {
    String WIN_TITLE = "RedditStat Graph Viewer";
    int WIN_WIDTH = 560;
    int WIN_HEIGHT = 367;

    public void openWindow();
    public void save(String filename);
}
