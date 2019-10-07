package me.quicktwix898.redditstatgrapher.ui;

public class FileNameScreen implements TerminalScreen {
    final static FileNameScreen INSTANCE = new FileNameScreen();
    final String message = DASHED_LINE + "\n" +
            "Please specify a directory and filename.\n" +
            "If you do not want to save a file, leave this blank.\n" +
            DASHED_LINE + "\n";
    @Override
    public String getDisplay() { return message; }

    public static FileNameScreen getInstance() { return INSTANCE; }

    @Override
    public FilePath getChoice(String c){
        return new FilePath(c);
    }

    public static class FilePath implements TerminalChoice {
        String path;

        private FilePath(String str){
            this.path = str;
        }

        @Override
        public TerminalAction getAction(){
            return null;
        }

        @Override
        public String getString(){
            return path;
        }
    }
}