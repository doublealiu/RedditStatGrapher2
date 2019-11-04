package me.quicktwix898.redditstatgrapher;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://www.db4free.net/redditstat");
        config.setUsername("wsmachine");
        config.setPassword("summerTwig678");
        System.out.println("Connecting...");
        HikariDataSource ds = new HikariDataSource(config);
        try{
            PreparedStatement statement = ds.getConnection().prepareStatement(
                    "SELECT title, self_text FROM subreddit_posts WHERE subreddit=? LIMIT 5000;");
            statement.setString(1, "pics");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                System.out.println(set.getString("title"));
            }
        }catch(SQLException e){
            System.out.println("ERROR WEE WOO WEE WOO");
            e.printStackTrace();
        }
    }
}
