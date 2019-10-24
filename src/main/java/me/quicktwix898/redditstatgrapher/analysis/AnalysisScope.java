package me.quicktwix898.redditstatgrapher.analysis;

public enum AnalysisScope {
    ALL("all_posts"),
    SUBREDDIT("subreddit_posts"),
    POST("tracked_posts");

    String tableName;

    AnalysisScope(String tableName){
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}