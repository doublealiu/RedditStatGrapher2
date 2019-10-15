package me.quicktwix898.redditstatgrapher;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.quicktwix898.redditstatgrapher.ui.TerminalScreen;
import me.quicktwix898.redditstatgrapher.ui.sql.SQLPasswordScreen;
import me.quicktwix898.redditstatgrapher.ui.sql.SQLURLScreen;
import me.quicktwix898.redditstatgrapher.ui.sql.SQLUsernameScreen;

import java.util.Scanner;

public class SQLUIHandler {
    public static HikariDataSource start(){
        Scanner scanner = new Scanner(System.in);
        HikariConfig config = new HikariConfig();

        new SQLURLScreen().action();
        config.setJdbcUrl(scanner.nextLine());

        new SQLUsernameScreen().action();
        config.setUsername( scanner.nextLine() );

        new SQLPasswordScreen().action();
        config.setPassword( "database_password" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        return new HikariDataSource(config);
    }
}
