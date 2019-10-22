package me.quicktwix898.redditstatgrapher;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.analysis.AnalysisAction;
import me.quicktwix898.redditstatgrapher.analysis.SubsInAllAnalysis;
import me.quicktwix898.redditstatgrapher.graph.GraphType;

import java.util.List;

public class ChoiceReader {
    List<Object> choices;
    HikariDataSource ds;

    public ChoiceReader (List<Object> choices, HikariDataSource ds){
        this.choices = choices;
        this.ds = ds;
    }

    public AnalysisAction get() {
        String first = ((String) choices.get(0)).toLowerCase();
        String second = ((String)choices.get(1)).toLowerCase();
        String third = ((String)choices.get(2)).toLowerCase();
        switch(first.charAt(0)){
            case('a'): { // r/all
                String filePath = (String)choices.get(3);
                switch(second.charAt(0)){
                    case('s'): { //subs in all
                        switch(third.charAt(0)){
                            case('b'): { //bar chart
<<<<<<< HEAD
                                return new SubsInAllAnalysis(GraphType.BAR, filePath, ds);
=======
                                System.out.println("subs in all bar graph");
                                //return new SubsInAllAnalysis(GraphType.BAR, filePath);
>>>>>>> bab535ed88bc626c0be3128edaacd187ec9b8007
                            } case('p'):{ // pie chart
                                System.out.println("subs in all pie graph");
                                //return new SubsInAllAnalysis(GraphType.PIE, filePath);
                            }
                        }
                    } case('w'): {
                        switch(third.charAt(0)) {
                            case('w'): {
                                System.out.println("word analysis word cloud");
                                // word analysis in all with word cloud
                            } case('b'): {
                                System.out.println("worl");
                                // word analysis with bar chart
                            } case('p'): {
                                System.out.println("subs in all bar graph");
                                // word analysis with pie chart
                            }
                        }
                    } case('p'): {
                        switch (third.charAt(0)) {
                            case('l'): {
                                // subreddit popularity over time in line chart
                            }
                        }
                    } case('a'): {
                        switch(third.charAt(0)) {
                            case('l'): {
                                // frequently active times in r/all in line chart
                            }
                            case('h'): {
                                // histogram frequently active times over 24h
                            }
                        }
                    }
                }
            } case('s'): {
                String fourth = ((String)choices.get(3)).toLowerCase();
                String filePath = (String)choices.get(4);
                switch(third.charAt(0)){
                    case('w'): {
                        switch(fourth.charAt(0)) {
                            case('w'): {
                                //frequently used words in subreddit word cloud
                            } case('p'): {
                                //frequently used words in subreddit pie chart
                            } case('b'): {
                                //frequently used words in subreddit baR CHART
                            }
                        }
                    } case('a'): {
                        switch (fourth.charAt(0)) {
                            case('l'): {
                                //frequently active times line chart
                            } case('h'): {
                                // frequently active times historgram (over 24h)
                            }
                        }
                    }
                }
            } case('p'): {
                switch(second.charAt(0)) {
                    case('w'): {
                        switch(third.charAt(0)) {
                            case('w'): {
                                // frequently used words in post word cloud
                            }
                            case('b'): {
                                // frequently used words bar chart
                            }
                            case('p'): {
                                //frequently used words pie chart
                            }
                        }
                    } case('u'): {
                        switch(third.charAt(0)) {
                            case('l'): {
                                // upvotes over time in post line chart
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
