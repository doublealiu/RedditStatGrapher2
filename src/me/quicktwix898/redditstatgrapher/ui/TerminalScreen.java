package me.quicktwix898.redditstatgrapher.ui;

public interface TerminalScreen extends TerminalAction {
    String DASHED_LINE = "------------------------------------------------------------";

    String getDisplay();

    TerminalChoice getChoice(String c);

    @Override
    default void action() {
        System.out.println(this.getDisplay());
    }
}