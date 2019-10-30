package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.BarGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphGenerator;
import me.quicktwix898.redditstatgrapher.graph.GraphType;
import me.quicktwix898.redditstatgrapher.graph.LineGenerator;

import java.sql.*;
import java.util.*;

public class FrequentlyActiveAnalysis implements AnalysisAction {
    private static final String GRAPH_TITLE = "Frequently Active Times Analysis";
    private static final int NUM_INTERVALS = 15;

    HikariDataSource ds;
    String file;
    GraphType type;
    AnalysisScope scope;

    GraphGenerator gen;
    String subreddit;
    //data
    final SortedMap<String, Integer> map = new TreeMap<>();

    public FrequentlyActiveAnalysis(HikariDataSource ds, String file, GraphType type, AnalysisScope scope) {
        this.ds = ds;
        this.file = file;
        this.type = type;
        this.scope = scope;
        query();
    }

    public FrequentlyActiveAnalysis(HikariDataSource ds, String file, GraphType type, AnalysisScope scope, String subreddit) {
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
                statement = ds.getConnection().prepareStatement("SELECT MIN(time) FROM subreddit_posts WHERE subreddit = ? AND " +
                        "time < NOW() AND time > ADDDATE(NOW(), INTERVAL -1 DAY) GROUP BY post_id LIMIT " + MAX_POSTS);
                statement.setString(1, subreddit);
            }else if(scope == AnalysisScope.ALL){
                statement = ds.getConnection().prepareStatement("SELECT MIN(time) FROM subreddit_posts " +
                        "WHERE time < NOW() AND time > ADDDATE(NOW(), INTERVAL -1 DAY) GROUP BY post_id LIMIT " + MAX_POSTS);
            }else{
                return;
            }
            ResultSet set = statement.executeQuery();
            //smallest time in index 0, biggest in last element
            List<Timestamp> timestamps = new ArrayList<>();
            while(set.next()){
                Timestamp time = set.getTimestamp("time");
                timestamps.add(time);
            }
            long intervalLength = (timestamps.get(timestamps.size() - 1).getTime() - timestamps.get(0).getTime())/NUM_INTERVALS + 1;
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < NUM_INTERVALS; i++){
                list.add(i);
            }
            for(Timestamp time : timestamps){
                int index = (int)((time.getTime() - timestamps.get(0).getTime())/intervalLength);
                list.set(index, list.get(index) + 1);
            }
            long min = timestamps.get(0).getTime();
            for(int i = 0; i < list.size(); i++){
                Integer val = list.get(i);
                String key = new Timestamp(min + (intervalLength) * i).toString() + " - " + new Timestamp(min + intervalLength * (i + 1)).toString();
                map.put(key, val);
            }
            if(type == GraphType.HISTOGRAM){
                gen = new BarGenerator(GRAPH_TITLE, map);
            }else if(type == GraphType.LINE){
                gen = new LineGenerator(GRAPH_TITLE, map);
            }else{
                return;
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
