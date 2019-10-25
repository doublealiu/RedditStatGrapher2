package me.quicktwix898.redditstatgrapher.ui.sql;

import me.quicktwix898.redditstatgrapher.ui.TerminalScreen;

public class SQLURLScreen implements TerminalScreen {
    final String message = DASHED_LINE + "\n" +
            "Please input the URL to your MySQL Server\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() {
        return message;
    }
}
