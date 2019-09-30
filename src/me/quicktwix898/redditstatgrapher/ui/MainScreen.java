package me.quicktwix898.redditstatgrapher.ui;

public class MainScreen implements TerminalScreen {

    final static MainScreen INSTANCE = new MainScreen();

    final String message = DASHED_LINE + "\n" +
            "RedditStat Grapher\n" +
            "Please choose an domain you want to analyze today: \n" +
            "a: r/all\n" +
            "s: a specific subreddit\n" +
            "p: a post\n" +
            DASHED_LINE + "\n";

    @Override
    public String getDisplay() {
        return message;
    }

    @Override
    public MainScreen getInstance() {
        return INSTANCE;
    }

    @Override
    public TerminalScreen getScreen(char c) {
        return MainScreenChoice.get(c).getScreen();
    }

    private enum MainScreenChoice implements TerminalChoice {
        all('a', ),
         subreddit('b', ),
        post('c', );

        char character;
        TerminalScreen screen;

        MainScreenChoice(char character, TerminalScreen screen){
            this.character = character;
            this.screen = screen;
        }

        @Override
        public char getCharacter() {
            return character;
        }

        @Override
        public TerminalScreen getScreen() {
            return screen;
        }

        public static MainScreenChoice get(char c){
            for(MainScreenChoice choice : values()) {
                if(choice.character == c){
                    return choice;
                }
            }
            return null;
        }
    }
}
