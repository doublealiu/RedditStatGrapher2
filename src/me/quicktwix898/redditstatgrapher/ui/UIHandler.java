package me.quicktwix898.redditstatgrapher.ui;

import java.util.Scanner;

public class UIHandler {
    //static
    final static UIHandler INSTANCE = new UIHandler(MainScreen.getInstance());

    public static UIHandler getInstance() {
        return INSTANCE;
    }

    //instance
    final Scanner scanner = new Scanner(System.in);
    TerminalScreen current;

    private UIHandler(TerminalScreen screen){
        current = screen;
    }

    public void start(){
        println(current.getDisplay());
        char input;
        while(current.getChoice(input = scanner.nextLine().charAt(0)) != null) {

        }
    }

    private static void println(String s){
        System.out.println(s);
    }
}
