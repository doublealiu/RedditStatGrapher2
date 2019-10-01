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

    public static TerminalScreen getInsstance() { return INSTANCE; }

    public PostAnalysisScreenChoice getChoice(char c) {
        return PostAnalysisScreenChoice.get(c);
    }

    public enum PostAnalysisScreenChoice implements TerminalChoice{
        words('w', ),
        upvotes('u', );

        char character;
        TerminalScreen screen;

        PostAnalysisScreenChoice(char character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }

        @Override
        public TerminalScreen getScreen{ return screen; }

        @Override
        public char getCharacter{ return character; }

        public static PostAnalysisScreenChoice get(char c) {
            for(PostAnalysisScreenChoice choice : value()) {
                if (choice.character == c) {
                    return choice;
                }
            }
            return null;
        }
    }
}
