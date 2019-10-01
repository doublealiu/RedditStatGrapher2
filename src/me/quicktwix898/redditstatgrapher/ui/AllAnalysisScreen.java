package me.quicktwix898.redditstatgrapher.ui;

import me.quicktwix898.redditstatgrapher.Main;

public class AllAnalysisScreen implements TerminalScreen {

    final static MainScreen INSTANCE = new MainScreen();

    final String message = DASHED_LINE + "\n" +
            "Please choose an analysis:\n" +
            "s: subreddits in r/all\n" +
            "w: frequently used words\n" +
            "p: subreddit popularity over time in r/all\n" +
            "a: frequently active times in r/all\n" +
            DASHED_LINE + "\n";
    @Override
    public String getDisplay() {
        return message;
    }

    @Override
    public TerminalScreen getInstance() {
        return INSTANCE;
    }

    public enum AllAnalysisScreenChoice implements TerminalChoice {
        subreddit('s', ),
        words('w', ),
        popularity('p', ),
        active('a', );

        char character;
        TerminalScreen screen;

        AllAnalysisScreenChoice(char character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }
        @Override
        public TerminalScreen getScreen() {
            return screen;
        }

        @Override
        public char getCharacter() {
            return character;
        }

        public static AllAnalysisScreenChoice get(char c){
            for(AllAnalysisScreenChoice choice : values()) {
                if(choice.character == c){
                    return choice;
                }
            }
            return null;
        }
    }
}