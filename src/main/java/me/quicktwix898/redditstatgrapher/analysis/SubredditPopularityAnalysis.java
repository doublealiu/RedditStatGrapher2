package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubredditPopularityAnalysis implements AnalysisAction {
    private static final String GRAPH_TITLE = "Subreddit Popularity Analysis";

    HikariDataSource ds;
    String file;
    GraphType type;
    AnalysisScope scope;

    GraphGenerator gen;
    String subreddit;

    public SubredditPopularityAnalysis(HikariDataSource ds, String file, GraphType type, AnalysisScope scope) {
        this.ds = ds;
        this.file = file;
        this.type = type;
        this.scope = scope;
        query();
    }

    public SubredditPopularityAnalysis(HikariDataSource ds, String file, GraphType type, AnalysisScope scope, String subreddit) {
        this.ds = ds;
        this.file = file;
        this.type = type;
        this.scope = scope;
        this.subreddit = subreddit;
    }

    @Override
    public void query(){
        try{
            PreparedStatement statement;
            if(scope == AnalysisScope.SUBREDDIT){
                statement = ds.getConnection().prepareStatement("SELECT MIN(time) FROM subreddit_posts subreddit = ? AND " +
                        "WHERE time < NOW() AND time > ADDDATE(NOW(), INTERVAL -1 DAY) GROUP BY post_id LIMIT " + MAX_POSTS);
                statement.setString(1, subreddit);
            }else if(scope == AnalysisScope.ALL){
                statement = ds.getConnection().prepareStatement("SELECT MIN(time) FROM subreddit_posts " +
                        "WHERE time < NOW() AND time > ADDDATE(NOW(), INTERVAL -1 DAY) GROUP BY post_id LIMIT " + MAX_POSTS);
            }else{
                return;
            }
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Timestamp time = set.getTimestamp("time");
            }
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
        if(file.equals("")){
            return;
        }
        gen.save(file);
    }
}
