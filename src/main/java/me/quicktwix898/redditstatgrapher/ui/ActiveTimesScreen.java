package me.quicktwix898.redditstatgrapher.ui;

import java.io.File;

public class ActiveTimesScreen  implements TerminalScreen {
    final static ActiveTimesScreen INSTANCE = new ActiveTimesScreen();

    final String message = DASHED_LINE + "\n" +
            "Please choose a type of graph: \n" +
            "l: line chart \n" +
            "h: histogram \n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    @Override
    public TerminalChoice getChoice(String c) { return ActiveTimesScreenChoice.get(c); }

    public enum ActiveTimesScreenChoice implements TerminalChoice {
        lineChart("l", PostNumberScreen.getInstance()),
        histogram("h", PostNumberScreen.getInstance());

        String character;
        TerminalScreen screen;

        ActiveTimesScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }

        @Override
        public TerminalScreen getAction() { return screen; }

        @Override
        public String getString() { return character; }

        public static ActiveTimesScreenChoice get(String c) {
            for (ActiveTimesScreenChoice choice : values()) {
                if(choice.character.equals(c)) {
                    return choice;
                }
            }
            return null;
        }
    }
}
