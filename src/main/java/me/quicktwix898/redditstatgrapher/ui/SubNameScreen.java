package me.quicktwix898.redditstatgrapher.ui;

public class SubNameScreen implements TerminalScreen {
    final static SubNameScreen INSTANCE = new SubNameScreen();

    final String message = DASHED_LINE + "\n" +
            "Please enter a subreddit name you want to analyze: \n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() { return message; }

    public static SubNameScreen getInstance() { return INSTANCE; }

    @Override
    public SubName getChoice(String c) { return new SubName(c); }

    public static class SubName implements TerminalChoice {
        String name;

        private SubName (String str) {
            this.name = str;
        }

        @Override
        public TerminalAction getAction() { return SubAnalysisScreen.getInstance(); }

        @Override
        public String getString() {
            return name;
        }
    }
}
