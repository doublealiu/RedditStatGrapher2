package me.quicktwix898.redditstatgrapher.ui;

public class PostUpvotesScreen implements TerminalScreen {
    final static PostUpvotesScreen INSTANCE = new PostUpvotesScreen();

    final String message = DASHED_LINE + "\n" +
            "Please choose a type of graph \n" +
            "l: line graph + \n";

    @Override
    public String getDisplay() { return message; }

    public static TerminalScreen getInstance() { return INSTANCE; }

    public TerminalChoice getChoice(String c) {
        return PostUpvotesScreenChoice.get(c);
    }

    public enum PostUpvotesScreenChoice implements TerminalChoice {
        line('l', );

        String character;
        TerminalScreen screen;

        PostUpvotesScreenChoice(String character, TerminalScreen screen) {
            this.character = character;
            this.screen = screen;
        }

        @Override
        public String getString() {
            return character;
        }

        @Override
        public TerminalScreen getAction() {
            return screen;
        }

        public static PostUpvotesScreenChoice get(String c) {
            for (PostUpvotesScreenChoice choice : values()) {
                if(choice.character.equals(c)) {
                    return choice;
                }
            }
            return null;
        }
    }
}
