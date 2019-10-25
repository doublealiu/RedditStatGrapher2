package me.quicktwix898.redditstatgrapher.analysis;

public interface AnalysisAction {
    void query();
    void graph();
    void save();

    int MAX_POSTS = 5000;
}
