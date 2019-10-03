package me.quicktwix898.redditstatgrapher.ui;

public interface TerminalScreen extends TerminalAction {
    String DASHED_LINE = "------------------------------------------------------------";

    String getDisplay();

    TerminalChoice getChoice(String s);

    @Override
    default void action() {
        System.out.println(this.getDisplay());
    }
}