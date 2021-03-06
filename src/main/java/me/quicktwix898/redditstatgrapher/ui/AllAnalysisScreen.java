package me.quicktwix898.redditstatgrapher.ui;

public class AllAnalysisScreen implements TerminalScreen {

    final static AllAnalysisScreen INSTANCE = new AllAnalysisScreen();

    final String message = DASHED_LINE + "\n" +
            "Please choose an analysis:\n" +
            "s: subreddits in r/all\n" +
            "w: frequently used words\n" +
            "a: frequently active times in r/all\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() {
        return message;
    }

    public static TerminalScreen getInstance() {
        return INSTANCE;
    }

    @Override
    public TerminalChoice getChoice(String c) { return AllAnalysisScreenChoice.get(c); }

    public enum AllAnalysisScreenChoice implements TerminalChoice {
        subreddit("s", AllSubScreen.getInstance()),
        words("w", FrequentWordsScreen.getInstance()),
        active("a", ActiveTimesScreen.getInstance());

        String character;
        TerminalScreen screen;

        AllAnalysisScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }
        @Override
        public TerminalScreen getAction() {
            return screen;
        }

        @Override
        public String getString() {
            return character;
        }

        public static AllAnalysisScreenChoice get(String c){
            for(AllAnalysisScreenChoice choice : values()) {
                if(choice.character.equals(c)){
                    return choice;
                }
            }
            return null;
        }
    }
}