package me.quicktwix898.redditstatgrapher.ui;

public interface TerminalChoice {
    TerminalScreen getScreen();
    char getCharacter();
    TerminalChoice get(char c);
}
