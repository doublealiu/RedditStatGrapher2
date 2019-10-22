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
                                return new SubsInAllAnalysis(GraphType.BAR, filePath, ds);
                            } case('p'):{ // pie chart

                            }
                        }
                    } case('w'): {

                    } case('p'): {

                    } case('a'): {

                    }
                }
            } case('s'): {

            } case('p'): {

            }
        }
        return null;
    }
}
