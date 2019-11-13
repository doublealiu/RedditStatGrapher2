package me.quicktwix898.redditstatgrapher.analysis;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.graph.*;
import org.graalvm.compiler.graph.iterators.PredicatedProxyNodeIterator;

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
    int max = 5000;
    GraphGenerator gen;

    public FrequentWordsAnalysis(HikariDataSource ds, GraphType type, String file, AnalysisScope scope, int max) {
        this.ds = ds;
        this.type = type;
        this.file = file;
        this.scope = scope;
        this.max = max;
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

    public FrequentWordsAnalysis(HikariDataSource ds, GraphType type, String file, AnalysisScope scope, String identifier, int max) {
        this.ds = ds;
        this.type = type;
        this.file = file;
        this.scope = scope;
        this.identifier = identifier;
        this.max = max;
        query();
    }

    @Override
    public void query() {
        try{
            PreparedStatement statement;
            if(scope == AnalysisScope.ALL){
                statement = ds.getConnection().prepareStatement("SELECT title, self_text FROM all_posts LIMIT " + max + ";");
            }else if(scope == AnalysisScope.SUBREDDIT){
                statement = ds.getConnection().prepareStatement("SELECT title, self_text FROM subreddit_posts WHERE subreddit = ? LIMIT " + max + ";");
                statement.setString(1, identifier);
            }else {
                statement = ds.getConnection().prepareStatement("SELECT title, self_text FROM tracked_posts WHERE post_id = ? LIMIT " + max + ";");
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
    static {
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
        removeWords.add("i\'ll");
        removeWords.add("from");
        removeWords.add("and");
        removeWords.add("they");
        removeWords.add("it\'s");
        removeWords.add("didn\'t");
        removeWords.add("i\'ve");
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
        removeWords.add("i\'m");
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
        removeWords.add("every");
        removeWords.add("can\'t");
        removeWords.add("really");
        removeWords.add("like");
        removeWords.add("such");
        removeWords.add("might");
        removeWords.add("already");
        removeWords.add("everything");
        removeWords.add("either");
        removeWords.add("myself");
        removeWords.add("else");
        removeWords.add("stop");
        removeWords.add("come");
        removeWords.add("then");
        removeWords.add("while");
        removeWords.add("tried");
        removeWords.add("going");
        removeWords.add("guys");
        removeWords.add("still");
        removeWords.add("maybe");
        removeWords.add("told");
        removeWords.add("felt");
        removeWords.add("once");
        removeWords.add("than");
        removeWords.add("back");
        removeWords.add("also");
        removeWords.add("more");
        removeWords.add("something");
        removeWords.add("through");
        removeWords.add("don't");
        removeWords.add("should");
        removeWords.add("getting");
        removeWords.add("before");
        removeWords.add("always");
        removeWords.add("were");
        removeWords.add("sure");
        removeWords.add("tell");
        removeWords.add("i've");
        removeWords.add("where");
        removeWords.add("until");
        removeWords.add("says");
        removeWords.add("said");
        removeWords.add("each");
        removeWords.add("him");
        removeWords.add("over");
        removeWords.add("many");
        removeWords.add("other");
        removeWords.add("call");
        removeWords.add("another");
        removeWords.add("same");
        removeWords.add("since");
        removeWords.add("day");
        removeWords.add("trying");
        removeWords.add("please");
        removeWords.add("people");
        removeWords.add("talk");
        removeWords.add("down");
        removeWords.add("full");
        removeWords.add("place");
        removeWords.add("doing");
        removeWords.add("idea");
        removeWords.add("think");
        removeWords.add("first");
        removeWords.add("away");
        removeWords.add("gets");
        removeWords.add("keep");
        removeWords.add("came");
        removeWords.add("went");
        removeWords.add("anything");
        removeWords.add("move");
        removeWords.add("good");
        removeWords.add("things");
        removeWords.add("next");
        removeWords.add("want");
        removeWords.add("never");
        removeWords.add("times");
        removeWords.add("thanks");
        removeWords.add("again");
        removeWords.add("both");
        removeWords.add("help");
        removeWords.add("must");
        removeWords.add("year");
        removeWords.add("being");
        removeWords.add("need");
        removeWords.add("around");
        removeWords.add("today");
        removeWords.add("out");
        removeWords.add("making");
        removeWords.add("someone");
        removeWords.add("join");
        removeWords.add("little");
        removeWords.add("months");
        removeWords.add("hours");
        removeWords.add("days");
        removeWords.add("look");
        removeWords.add("looking");
        removeWords.add("wondering");
        removeWords.add("woman");
        removeWords.add("person");
        removeWords.add("side");
        removeWords.add("thought");
        removeWords.add("together");
        removeWords.add("feel");
        removeWords.add("find");
        removeWords.add("doesn't");
        removeWords.add("etc");
        removeWords.add("kept");
        removeWords.add("led");
        removeWords.add("thing");
        removeWords.add("best");
        removeWords.add("care");
        removeWords.add("great");
        removeWords.add("recently");
        removeWords.add("last");
        removeWords.add("without");
        removeWords.add("however");
        removeWords.add("made");
        removeWords.add("info");
        removeWords.add("case");
        removeWords.add("thinking");
        removeWords.add("stuff");
        removeWords.add("three");
        removeWords.add("past");
        removeWords.add("enjoy");
        removeWords.add("able");
        removeWords.add("done");
        removeWords.add("against");
        removeWords.add("finish");
        removeWords.add("looked");
        removeWords.add("took");
        removeWords.add("lost");
        removeWords.add("happened");
        removeWords.add("though");
        removeWords.add("feels");
        removeWords.add("well");
        removeWords.add("true");
        removeWords.add("actually");
        removeWords.add("almost");
        removeWords.add("point");
        removeWords.add("second");
        removeWords.add("etc.");
        removeWords.add("later");
        removeWords.add("whole");
        removeWords.add("between");
        removeWords.add("years");
        removeWords.add("ever");
        removeWords.add("under");
        removeWords.add("meet");
        removeWords.add("list");
        removeWords.add("reasons");
        removeWords.add("take");
        removeWords.add("start");
        removeWords.add("used");
        removeWords.add("those");
        removeWords.add("seconds");
        removeWords.add("started");
        removeWords.add("during")


        // offensive language in base64
        String[] offensive = {"bmlnZ2Vy", "bmlnZ2E=", "bmlnYQ==", "ZmVtb2lk", "bmlxcWE=", "ZmFnZ290", "ZmFn", "YmVhbmVy", "c3BpYw==", "Y2hpbms=", "ZGVlcHdhdGVy"};
        for(String x : offensive) {
            removeWords.add(new String(Base64.getMimeDecoder().decode(x)));
        }
    }
}
