package me.quicktwix898.redditstatgrapher.ui;

public class PostIDScreen implements TerminalScreen {
    final static PostIDScreen INSTANCE = new PostIDScreen();
    final String message = DASHED_LINE + "\n" +
            "Please specify the alphnumeric ID of the post you want to analyze: \n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static PostIDScreen getInstance() { return INSTANCE; }

    @Override
    public PostID getChoice(String c) { return new PostID(c); }

    public static class PostID implements TerminalChoice {
        String ID;

        private PostID (String str) { this.ID = str; }

        @Override
        public TerminalAction getAction() { return PostAnalysisScreen.getInstance(); }

        @Override
        public String getString() {
            return ID;
        }
    }
}
