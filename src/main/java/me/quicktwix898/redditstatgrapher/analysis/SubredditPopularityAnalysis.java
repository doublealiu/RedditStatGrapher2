package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;
import me.quicktwix898.redditstatgrapher.graph.LineGenerator;
import me.quicktwix898.redditstatgrapher.ui.FrequentWordsScreen;
import org.apache.commons.lang3.time.DateUtils;
import org.jfree.date.DateUtilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class SubredditPopularityAnalysis implements AnalysisAction {
    private final static String TITLE = "Subreddit Popularity Analysis";

    HikariDataSource ds;
    String file;

    GraphGenerator gen;
    String subreddit;
    final SortedMap<String, Integer> map = new TreeMap<>();

    public SubredditPopularityAnalysis(HikariDataSource ds, String file, String subreddit) {
        this.ds = ds;
        this.file = file;
        this.subreddit = subreddit;
        query();
    }

    @Override
    public void query(){
        try {
            PreparedStatement statement = ds.getConnection().prepareStatement("SELECT time FROM all_posts WHERE subreddit=? LIMIT " + MAX_POSTS);
            statement.setString(1, subreddit);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                map.merge(DateUtils.ceiling(new Date(set.getTime("").getTime()), Calendar.MINUTE).toString(), 1, Integer::sum);
            }
            gen = new LineGenerator(TITLE, map);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void graph(){
        gen.openWindow();
    }

    @Override
    public void save(){
        if(!file.equals("")){
            gen.save(file);
        }
    }
}
