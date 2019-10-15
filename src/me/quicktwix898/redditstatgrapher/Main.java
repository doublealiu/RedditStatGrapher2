package me.quicktwix898.redditstatgrapher;

import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

public class Main {

    public static void main(String[] args){
        HikariDataSource ds = SQLUIHandler.start();
        List<Object> choices = UIHandler.getInstance().start();
    }
}
