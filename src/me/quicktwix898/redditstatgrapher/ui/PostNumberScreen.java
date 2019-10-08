package me.quicktwix898.redditstatgrapher.ui;

import java.text.ParseException;

public class PostNumberScreen implements TerminalScreen {
    final static PostNumberScreen INSTANCE = new PostNumberScreen();
    final static int MAX_POSTS = 5000;

    final String message  = DASHED_LINE + "\n" +
            "Please input the maximum number of posts you would like to select in your query.\n" +
            "Input a blank line if you want to select the maximum number of posts(" + MAX_POSTS + "):\n" +
            DASHED_LINE + "\n";

    final String error = DASHED_LINE + "\n" +
            "That is not a valid number.\n" +
            "Please try again: \n" +
            DASHED_LINE + "\n";

    public static PostNumberScreen getInstance(){
        return INSTANCE;
    }

    @Override
    public String getDisplay(){
        return message;
    }

    @Override
    public PostNumber getChoice(String str){
        try{
            int i = Integer.parseInt(str);
            return new PostNumber(i);
        } catch(NumberFormatException e){
            return null;
        }
    }

    @Override
    public String error(){
        return error;
    }

    public static class PostNumber implements TerminalChoice {
        int number;

        private PostNumber(int i){
            number = i;
        }

        @Override
        public TerminalAction getAction(){
            return FileNameScreen.getInstance();
        }

        @Override
        public String getString(){
            return "" + number;
        }
    }
}
