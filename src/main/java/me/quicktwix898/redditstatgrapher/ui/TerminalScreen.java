package me.quicktwix898.redditstatgrapher.ui;

public interface TerminalScreen extends TerminalAction {
    String DASHED_LINE = "------------------------------------------------------------";

    String getDisplay();

    default TerminalChoice getChoice(String c){
        return null;
    }

    default String error(){
        return getDisplay();
    }

    @Override
    default void action() {
        System.out.println(this.getDisplay());
    }
}