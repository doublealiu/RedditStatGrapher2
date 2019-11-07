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
        removeWords.add("the");
        removeWords.add("be");
        removeWords.add("to");
        removeWords.add("of");
        removeWords.add("and");
        removeWords.add("a");
        removeWords.add("in");
        removeWords.add("that");
        removeWords.add("have");
        removeWords.add("it");
        removeWords.add("for");
        removeWords.add("not");
        removeWords.add("as");
        removeWords.add("this");
        removeWords.add("do");
        removeWords.add("an");
        removeWords.add("them");
        removeWords.add("no");
        removeWords.add("these");
        removeWords.add("i'll");
        removeWords.add("from");
        removeWords.add("and");
        removeWords.add("they");
        removeWords.add("it's");
        removeWords.add("didn't");
        removeWords.add("i've");
        removeWords.add("i");
        removeWords.add("is");
        removeWords.add("my");
        removeWords.add("you");
        removeWords.add("with");
        removeWords.add("but");
        removeWords.add("was");
        removeWords.add("or");
        removeWords.add("|");
        removeWords.add("are");
        removeWords.add("just");
        removeWords.add("at");
        removeWords.add("me");
        removeWords.add("so");
        removeWords.add("on");
        removeWords.add("what");
        removeWords.add("can");
        removeWords.add("we");
        removeWords.add("if");
        removeWords.add("your");
        removeWords.add("-");
        removeWords.add("about");
        removeWords.add("get");
        removeWords.add("been");
        removeWords.add("any");
        removeWords.add("know");
        removeWords.add("dont");
        removeWords.add("there");
        removeWords.add("will");
        removeWords.add("up");
        removeWords.add("he");
        removeWords.add("when");
        removeWords.add("one");
        removeWords.add("would");
        removeWords.add("has");
        removeWords.add("i'm");
        removeWords.add("by");
        removeWords.add("had");
        removeWords.add("how");
        removeWords.add("some");
        removeWords.add("could");
        removeWords.add("our");
        removeWords.add("their");
        removeWords.add("only");
        removeWords.add("into");
        removeWords.add("which");
        removeWords.add("because");
        removeWords.add("even");
        removeWords.add("her");
        removeWords.add("now");
        removeWords.add("am");
        removeWords.add("who");
        removeWords.add("make");
        removeWords.add("anyone");

    }
}
