package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;
import me.quicktwix898.redditstatgrapher.graph.LineGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;

public class UpvotesOverTimeAnalysis implements AnalysisAction {
    private static final String TITLE = "Line Graph Analysis for Points Over Time";

    HikariDataSource ds;
    String file;
    String postId;
    GraphGenerator gen;
    int max = 5000;

    public UpvotesOverTimeAnalysis(HikariDataSource ds, String file, String postId) {
        this.ds = ds;
        this.file = file;
        this.postId = postId;
        query();
    }

    public UpvotesOverTimeAnalysis(HikariDataSource ds, String file, String postId, int max) {
        this.ds = ds;
        this.file = file;
        this.postId = postId;
        this.max = max;
        query();
    }

    @Override
    public void query() {
        try{
            PreparedStatement statement = ds.getConnection().prepareStatement("SELECT time, points FROM tracked_posts WHERE post_id=? LIMIT " + max + ";");
            statement.setString(1, postId);
            ResultSet set = statement.executeQuery();
            SortedMap<String, Integer> map = new TreeMap<>();
            while(set.next()){
                map.put(set.getTime("time").toString(), set.getInt("points"));
            }
            gen = new LineGenerator(TITLE, map);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void graph() {
        gen.openWindow();
    }

    @Override
    public void save() {
        if(!file.equals("")){
            gen.save(file);
        }
    }
}
