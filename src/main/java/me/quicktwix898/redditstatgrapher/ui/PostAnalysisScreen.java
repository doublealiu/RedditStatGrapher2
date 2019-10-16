package me.quicktwix898.redditstatgrapher.ui;

public class PostAnalysisScreen implements TerminalScreen {
    final static PostAnalysisScreen INSTANCE = new PostAnalysisScreen();
    final String message = DASHED_LINE + "\n" +
            "Please choose an analysis:\n" +
            "w: frequently used words\n" +
            "u: upvotes over time\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    public TerminalChoice getChoice(String c) {
        return PostAnalysisScreenChoice.get(c);
    }

    public enum PostAnalysisScreenChoice implements TerminalChoice{
        words("w", FrequentWordsPostScreen.getInstance()),
        upvotes("u", PostUpvotesScreen.getInstance());

        String character;
        TerminalScreen screen;

        PostAnalysisScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }

        @Override
        public TerminalScreen getAction(){ return screen; }

        @Override
        public String getString(){ return character; }

        public static PostAnalysisScreenChoice get(String c) {
            for(PostAnalysisScreenChoice choice : values()) {
                if (choice.character.equals(c)) {
                    return choice;
                }
            }
            return null;
        }
    }
}
