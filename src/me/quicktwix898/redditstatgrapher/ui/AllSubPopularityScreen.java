package me.quicktwix898.redditstatgrapher.ui;

public class AllSubPopularityScreen implements TerminalScreen {
    final static AllSubPopularityScreen INSTANCE = new AllSubPopularityScreen();

    // currently there will only be one type of graph for the popularity of a subreddit in r/all
    // this may change in the future so I am keeping this here
    final String message = DASHED_LINE + "\n" +
            "Please choose a type of graph: \n" +
            "l: line chart" + DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    @Override
    public TerminalChoice getChoice(String c) { return AllSubPopularityScreenChoice.get(c); }

    public enum AllSubPopularityScreenChoice implements  TerminalChoice {
        line("l", FileNameScreen.getInstance());

        String character;
        TerminalScreen screen;

        AllSubPopularityScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }

        @Override
        public TerminalScreen getAction() { return screen; }

        @Override
        public String getString() { return character; }

        public static AllSubPopularityScreenChoice get(String c) {
            for(AllSubPopularityScreenChoice choice : values()) {
                if(choice.character.equals(c)) {
                    return choice;
                }
            }
            return null;
        }
    }
}
