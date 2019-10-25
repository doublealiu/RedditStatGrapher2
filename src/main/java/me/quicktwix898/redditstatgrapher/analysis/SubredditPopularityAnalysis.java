package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;

public class SubredditPopularityAnalysis implements AnalysisAction {
    private static final String GRAPH_TITLE = "Frequent Words ";

    HikariDataSource ds;
    String file;

    GraphGenerator gen;

    public SubredditPopularityAnalysis(HikariDataSource ds, String file) {
        this.ds = ds;
        this.file = file;
        query();
    }

    @Override
    public void query(){

    }

    @Override
    public void graph(){

    }

    @Override
    public void save(){

    }
}
