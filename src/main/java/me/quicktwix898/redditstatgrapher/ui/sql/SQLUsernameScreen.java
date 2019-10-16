package me.quicktwix898.redditstatgrapher.ui.sql;

import me.quicktwix898.redditstatgrapher.ui.TerminalScreen;

public class SQLUsernameScreen implements TerminalScreen {
    final String message = DASHED_LINE + "\n" +
            "Please input the username of an account on your SQL Server.\n" +
            "Make sure that this account has query permissions.\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay(){
        return  message;
    }
}
