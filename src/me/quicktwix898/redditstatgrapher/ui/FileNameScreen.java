package me.quicktwix898.redditstatgrapher.ui;

public class FileNameScreen implements TerminalScreen {
    final static FileNameScreen INSTANCE = new FileNameScreen();
    final String message = DASHED_LINE + "\n" +
            "Please specify a directory and filename: \n" +
            DASHED_LINE + "\n";
    @Override
    public String getDisplay() { return message; }

    public static FileNameScreen getInstance() { return INSTANCE; }

    @Override
    public TerminalChoice getChoice(char c) //TODO a
}
