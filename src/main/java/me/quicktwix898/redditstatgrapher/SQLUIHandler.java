package me.quicktwix898.redditstatgrapher;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import me.quicktwix898.redditstatgrapher.ui.sql.SQLPasswordScreen;
import me.quicktwix898.redditstatgrapher.ui.sql.SQLURLScreen;
import me.quicktwix898.redditstatgrapher.ui.sql.SQLUsernameScreen;

import java.sql.SQLException;
import java.util.Scanner;

public class SQLUIHandler {
    public static HikariDataSource start() {
        Scanner scanner = new Scanner(System.in);
        HikariConfig config = new HikariConfig();
        boolean keepLooping = true;

        while (keepLooping){
            new SQLURLScreen().action();
            config.setJdbcUrl("jdbc:mysql://" + scanner.nextLine() + "/redditstat");

            new SQLUsernameScreen().action();
            config.setUsername(scanner.nextLine());

            new SQLPasswordScreen().action();
            config.setPassword(scanner.nextLine());
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            try {
                return new HikariDataSource(config);
            } catch (HikariPool.PoolInitializationException e) {
                System.out.println("An error occured while trying to create a connection to the database. " +
                        "\nPlease try again");
            /* server address wrong
            Failed to initialize pool: Communications link failure

            The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
             */

            /*user is bad
            Failed to initialize pool: Access denied for user 'd'@'216.172.252.11' (using password: YES)
             */
            }
        }
        return null;
    }
}
