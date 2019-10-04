package me.quicktwix898.redditstatgrapher.ui;

public class SubAnalysisScreen implements TerminalScreen {
    final static SubAnalysisScreen INSTANCE = new SubAnalysisScreen();
    final String message = DASHED_LINE + "\n" +
            "Please choose an analysis:\n" +
            "w: frequently used words\n" +
            "a: frequently active times in subreddit\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    @Override
    public TerminalChoice getChoice(String c) {
        return SubAnalysisScreenChoice.get(c);
    }

    public enum SubAnalysisScreenChoice implements TerminalChoice{
        words('w', ),
        active('a', );

        String character;
        TerminalScreen screen;

        SubAnalysisScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }
        @Override
        public TerminalScreen getScreen() { return screen; }

        @Override
        public String getString() { return character; }

        public static SubAnalysisScreenChoice get(char c) {
            for(SubAnalysisScreenChoice choice : values()) {
                if(choice.character == c) {
                    return choice;
                }
            }
            return null;
        }
    }
}
