package me.quicktwix898.redditstatgrapher.graph;


import org.jfree.chart.JFreeChart;

import java.util.*;

public interface GraphGenerator {
    String WIN_TITLE = "RedditStat Graph Viewer";
    int WIN_WIDTH = 560;
    int WIN_HEIGHT = 367;
    int MAX_CATEGORY_ENTRIES = 15;

    void openWindow();
    void save(String filename);

    static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        LinkedHashMap<K, V> reverse = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> reverse.put(x.getKey(), x.getValue()));
        return reverse;
    }
}
