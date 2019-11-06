package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FrequentWordsAnalysis implements AnalysisAction {
    private static final String GRAPH_TITLE = "Frequent Words Analysis";

    HikariDataSource ds;
    GraphType type;
    String file;
    AnalysisScope scope;
    //data
    final Map<String, Integer> map = new HashMap<>();
    final List<String> list = new ArrayList<>();
    //optional
    String identifier;
    GraphGenerator gen;

    public FrequentWordsAnalysis(HikariDataSource ds, GraphType type, String file, AnalysisScope scope) {
        this.ds = ds;
        this.type = type;
        this.file = file;
        this.scope = scope;
        query();
    }

    public FrequentWordsAnalysis(HikariDataSource ds, GraphType type, String file, AnalysisScope scope, String identifier) {
        this.ds = ds;
        this.type = type;
        this.file = file;
        this.scope = scope;
        this.identifier = identifier;
        query();
    }

    @Override
    public void query() {
        try{
            PreparedStatement statement;
            if(scope == AnalysisScope.ALL){
                statement = ds.getConnection().prepareStatement("SELECT title, self_text FROM all_posts LIMIT " + MAX_POSTS + ";");
            }else if(scope == AnalysisScope.SUBREDDIT){
                statement = ds.getConnection().prepareStatement("SELECT title, self_text FROM subreddit_posts WHERE subreddit = ? LIMIT " + MAX_POSTS + ";");
                statement.setString(1, identifier);
            }else {
                statement = ds.getConnection().prepareStatement("SELECT title, self_text FROM tracked_posts WHERE post_id = ? LIMIT " + MAX_POSTS + ";");
                statement.setString(1, identifier);
            }
            ResultSet set = statement.executeQuery();
            while(set.next()){
                System.out.println(set.getString("title"));
                Collections.addAll(list, set.getString("title").toLowerCase().split(" "));
                Collections.addAll(list, set.getString("self_text").toLowerCase().split(" "));
            }
            for(String str : list){
                System.out.println(str);
                if(map.get(str) == null){
                    map.put(str, 1);
                }else{
                    map.put(str, map.get(str) + 1);
                }
            }
            removeCommon(list, map);
            if(type == GraphType.BAR){
                gen = new BarGenerator(GRAPH_TITLE, map);
            }else if(type == GraphType.PIE){
                gen = new PieGenerator(GRAPH_TITLE, map);
            }else if (type == GraphType.WORDCLOUD){
                gen = new WordCloudGenerator(list);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void removeCommon(List<String> list, Map<String, Integer> map){
        for(String str : removeWords){
            while(list.contains(str)){
                list.remove(str);
            }
            map.put(str, 0);
        }
    }

    @Override
    public void graph() {
        if(file.equals(""))
            return;
        gen.save(file);
    }

    @Override
    public void save() {
        gen.openWindow();
    }

    private static final Set<String> removeWords = new HashSet<>();
    {
        removeWords.add("");
        removeWords.add(" ");
    }
}
