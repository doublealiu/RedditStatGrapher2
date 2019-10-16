package me.quicktwix898.redditstatgrapher.ui.sql;

import me.quicktwix898.redditstatgrapher.ui.TerminalScreen;

public class SQLPasswordScreen implements TerminalScreen {
    final String message = DASHED_LINE + "\n" +
            "Please input the password of the corresponding account on your SQL Server:\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() {
        return message;
    }
}
