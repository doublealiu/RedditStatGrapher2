package me.quicktwix898.redditstatgrapher;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.analysis.*;
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
                                return new SubsInAllAnalysis(GraphType.BAR, filePath, ds);
                            } case('p'):{ // pie chart
                                System.out.println("subs in all pie graph");
                                return new SubsInAllAnalysis(GraphType.PIE, filePath, ds);
                            }
                        }
                    } case('w'): {
                        switch(third.charAt(0)) {
                            case('w'): {
                                return new FrequentWordsAnalysis(ds, GraphType.WORDCLOUD, filePath, AnalysisScope.ALL);
                            } case('b'): {
                                return new FrequentWordsAnalysis(ds, GraphType.BAR, filePath, AnalysisScope.ALL);
                            } case('p'): {
                                return new FrequentWordsAnalysis(ds, GraphType.PIE, filePath, AnalysisScope.ALL);
                            }
                        }
                    } case('a'): {
                        switch(third.charAt(0)) {
                            case('l'): {
                                return new FrequentlyActiveAnalysis(ds, filePath, GraphType.LINE, AnalysisScope.ALL);
                            }
                            case('h'): {
                                return new FrequentlyActiveAnalysis(ds, filePath, GraphType.HISTOGRAM, AnalysisScope.ALL);
                            }
                        }
                    }
                }
            } case('s'): {
                String fourth = ((String)choices.get(3)).toLowerCase();
                int maxPosts = (int)choices.get(4);
                String filePath = (String)choices.get(5);
                switch(third.charAt(0)){
                    case('w'): {
                        switch(fourth.charAt(0)) {
                            case('w'): {
                                return new FrequentWordsAnalysis(ds, GraphType.WORDCLOUD, filePath, AnalysisScope.SUBREDDIT, second);
                            } case('p'): {
                                return new FrequentWordsAnalysis(ds, GraphType.PIE, filePath, AnalysisScope.SUBREDDIT, second);
                            } case('b'): {
                                return new FrequentWordsAnalysis(ds, GraphType.BAR, filePath, AnalysisScope.SUBREDDIT, second);
                            }
                        }
                    } case('a'): {
                        switch (fourth.charAt(0)) {
                            case('l'): {
                                return new FrequentlyActiveAnalysis(ds, filePath, GraphType.LINE, AnalysisScope.SUBREDDIT, second);
                            } case('h'): {
                                return new FrequentlyActiveAnalysis(ds, filePath, GraphType.HISTOGRAM, AnalysisScope.SUBREDDIT, second);
                            }
                        }
                    } case('p'): {
                        switch (third.charAt(0)) {
                            case('l'): {
                                return new SubredditPopularityAnalysis(ds, filePath, second);
                            }
                        }
                    }
                }
            } case('p'): {
                //second is post id
                String fourth = (String)choices.get(3);
                String filePath = (String)choices.get(4);
                switch(third.charAt(0)) {
                    case('w'): {
                        switch(fourth.charAt(0)) {
                            case('w'): {
                                return new FrequentWordsAnalysis(ds, GraphType.WORDCLOUD, filePath, AnalysisScope.POST, second);
                            }
                            case('b'): {
                                return new FrequentWordsAnalysis(ds, GraphType.BAR, filePath, AnalysisScope.POST, second);
                            }
                            case('p'): {
                                return new FrequentWordsAnalysis(ds, GraphType.PIE, filePath, AnalysisScope.POST, second);
                            }
                        }
                    } case('u'): {
                        switch(third.charAt(0)) {
                            case('l'): {
                                return new UpvotesOverTimeAnalysis(ds, filePath, second);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
