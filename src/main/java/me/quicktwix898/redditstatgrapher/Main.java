package me.quicktwix898.redditstatgrapher;

import java.util.List;

import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.analysis.AnalysisAction;

public class Main {

    public static void main(String[] args){
        HikariDataSource ds = SQLUIHandler.start();
        List<Object> choices = UIHandler.getInstance().start();
        System.out.println("Making a database query...");
        AnalysisAction analysis = new ChoiceReader(choices, ds).get();
        System.out.println("Creating your graphs...");
        analysis.graph();
        analysis.save();
    }
}
