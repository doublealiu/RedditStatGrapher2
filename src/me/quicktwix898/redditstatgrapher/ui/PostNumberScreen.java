package me.quicktwix898.redditstatgrapher.ui;

public class PostNumberScreen implements TerminalScreen {
    final static PostNumberScreen INSTANCE = new PostNumberScreen();
    final static int MAX_POSTS = 5000;

    final String message  = DASHED_LINE + "\n" +
            "Please input the maximum number of posts you would like to select in your query.\n" +
            "Input a blank line if you want to select the maximum number of posts(" + MAX_POSTS + "):\n" +
            DASHED_LINE;

    public static PostNumberScreen getInstance(){
        return INSTANCE;
    }

    @Override
    public String getDisplay(){
        return message;
    }

    @Override
    public PostNumber getChoice(String str){
        return null;
    }

    public static class PostNumber implements TerminalChoice {
        final static TerminalAction ACTION = FileNameScreen.getInstance();

        int number;

        private
    }
}
