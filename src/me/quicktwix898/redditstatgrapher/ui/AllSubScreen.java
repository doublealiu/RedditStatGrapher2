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
    public TerminalChoice getChoice(String c) { return AllSubScreenChoice.get(c); }

    public enum AllSubScreenChoice implements TerminalChoice{
        bar('b', ),
        pie('p', );

        String character;
        TerminalScreen screen;

        AllSubScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }
        @Override
        public TerminalScreen getAction() { return screen; }

        @Override
        public String getString() { return character; }

        public static AllSubScreenChoice get(String c) {
            for(AllSubScreenChoice choice : values()) {
                if (choice.character.equals(c)) {
                    return choice;
                }
            }
            return null;
        }
    }
}
