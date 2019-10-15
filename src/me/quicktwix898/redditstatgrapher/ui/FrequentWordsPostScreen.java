package me.quicktwix898.redditstatgrapher.ui;

public class FrequentWordsPostScreen implements TerminalScreen {
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
    public TerminalChoice getChoice(String c)  { return FrequentWordsPostScreen.FrequentWordsScreenChoice.get(c); }

    //must be 1 if you are doing a post
    public enum FrequentWordsScreenChoice implements TerminalChoice {
        wordCloud("w"),
        bar("b"),
        pie("p");

        String character;
        final TerminalScreen screen = FileNameScreen.getInstance();

        FrequentWordsScreenChoice(String character) {
            this.character = character;
        }
        @Override
        public TerminalScreen getAction() { return screen; }

        @Override
        public String getString() { return character; }

        public static FrequentWordsPostScreen.FrequentWordsScreenChoice get(String c) {
            for (FrequentWordsPostScreen.FrequentWordsScreenChoice choice : values()) {
                if (choice.character.equals(c)) {
                    return choice;
                }
            }
            return null;
        }
    }
}
