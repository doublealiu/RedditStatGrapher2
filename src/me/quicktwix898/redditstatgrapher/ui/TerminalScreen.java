package me.quicktwix898.redditstatgrapher.ui;

public interface TerminalScreen {
    String DASHED_LINE = "------------------------------------------------------------";

    String getDisplay();

    TerminalScreen getInstance();
}
