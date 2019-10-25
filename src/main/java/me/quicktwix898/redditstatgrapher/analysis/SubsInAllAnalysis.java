package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.BarGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;
import me.quicktwix898.redditstatgrapher.graph.PieGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SubsInAllAnalysis implements AnalysisAction {
    private static final String GRAPH_TITLE = "Subreddits in r/all";

    GraphType type;
    String file;
    HikariDataSource ds;
    final Map<String, Integer> map = new HashMap<>();
    GraphGenerator gen;

    public SubsInAllAnalysis(GraphType type, String file, HikariDataSource ds){
        this.type = type;
        this.file = file;
        this.ds = ds;
        query();
    }

    @Override
    public void query(){
        try{
            PreparedStatement statement = ds.getConnection().prepareStatement("SELECT subreddit FROM all_posts LIMIT " + MAX_POSTS + ";");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                String str = result.getString("subreddit");
                map.merge(str, 1, Integer::sum);
            }
            if(type == GraphType.BAR){
                gen = new BarGenerator(GRAPH_TITLE, map);
            }else if(type == GraphType.PIE){
                gen = new PieGenerator(GRAPH_TITLE, map);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(){
        if(file.equals(""))
            return;
        gen.save(file);
    }

    @Override
    public void graph(){
        gen.openWindow();
    }
}
