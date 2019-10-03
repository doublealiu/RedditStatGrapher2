package me.quicktwix898.redditstatgrapher.ui;

public class AllSubScreen implements TerminalScreen {
    final static AllSubScreen INSTANCE = new AllSubScreen();
    final String message = DASHED_LINE + "\n" +
            "Please choose a type of chart: " + "\n" +
            "b: Bar graph" + "\n" +
            "p: Pie chart" + "\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    @Override
    public TerminalAction getAction(char c) { return AllSubScreenChoice.get(c).getScreen; }

    public enum AllSubScreenChoice
}
