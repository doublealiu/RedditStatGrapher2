package me.quicktwix898.redditstatgrapher.analysis;

import me.quicktwix898.redditstatgrapher.graph.GraphType;

import java.io.File;

public class SubsInAllAnalysis implements AnalysisAction {
    GraphType type;
    File file;

    public SubsInAllAnalysis(GraphType type, String file){
        this.type = type;
        if(!file.equals("")){
            this.file = new File(file);
        }
    }

    @Override
    public void query(){

    }
}
