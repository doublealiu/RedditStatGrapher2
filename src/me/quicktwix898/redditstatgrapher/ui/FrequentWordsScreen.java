package me.quicktwix898.redditstatgrapher.ui;

public class FrequentWordsScreen implements TerminalScreen {
    final static FrequentWordsScreen INSTANCE = new FrequentWordsScreen();

    final String message = DASHED_LINE + "\n" +
            "Please choose a type of graph: \n" +
            "w: word cloud \n" +
            "b: bar chart \n" +
            "p: pie chart \n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    @Override
    public TerminalChoice getChoice(String c)  { return FrequentWordsScreenChoice.get(c); }

    public enum FrequentWordsScreenChoice implements TerminalChoice {
        wordCloud('w', ),
        bar('b', ),
        pie('p', );

        char character;
        TerminalScreen screen;

        FrequentWordsScreenChoice(char character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }
        @Override
        public TerminalScreen getScreen() { return screen; }

        @Override
        public String getString() { return character; }

        public static FrequentWordsScreenChoice get(char c) {
            for (FrequentWordsScreenChoice choice : values()) {
                if (choice.character == c) {
                    return choice;
                }
            }
            return null;
        }
    }
}
