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
    public TerminalChoice getChoice(String s) { return AllSubScreenChoice.get(s); }

    public enum AllSubScreenChoice implements TerminalChoice{
        bar('b', ),
        pie('p', );

        char character;
        TerminalScreen screen;

        AllSubScreenChoice(char character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }
        @Override
        public TerminalScreen getScreen() { return screen; }

        @Override
        public char getCharacter() { return character; }

        public static AllSubScreenChoice get(char c) {
            for(AllSubScreenChoice choice : values()) {
                if (choice.character == c) {
                    return choice;
                }
            }
            return null;
        }
    }
}
