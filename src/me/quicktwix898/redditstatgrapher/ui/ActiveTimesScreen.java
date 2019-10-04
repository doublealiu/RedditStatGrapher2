package me.quicktwix898.redditstatgrapher.ui;

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
    public TerminalChoice getChoice(char c) { return ActiveTimesScreenChoice.get(c); }

    public enum ActiveTimesScreenChoice implements TerminalChoice {
        lineChart('l', ),
        histogram('h', );

        char character;
        TerminalScreen screen;

        ActiveTimesScreenChoice(char character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }

        @Override
        public TerminalScreen getScreen() { return screen; }

        @Override
        public char getCharacter() { return character; }

        public static ActiveTimesScreenChoice get(char c) {
            for (ActiveTimesScreenChoice choice : values()) {
                if(choice.character == c) {
                    return choice;
                }
            }
            return null;
        }
    }
}
