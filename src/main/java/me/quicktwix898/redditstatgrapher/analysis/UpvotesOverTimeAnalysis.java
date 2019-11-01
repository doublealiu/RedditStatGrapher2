package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpvotesOverTimeAnalysis implements AnalysisAction {
    HikariDataSource ds;
    String file;
    String postId;
    GraphGenerator gen;

    public UpvotesOverTimeAnalysis(HikariDataSource ds, String file, String postId) {
        this.ds = ds;
        this.file = file;
        this.postId = postId;
    }

    @Override
    public void query() {
        try{
            PreparedStatement statement = ds.getConnection().prepareStatement("SELECT time, points FROM tracked_posts WHERE post_id=? LIMIT " + MAX_POSTS);
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
