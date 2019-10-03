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
    TerminalAction currentScreen;

    private UIHandler(TerminalScreen screen){
        currentScreen = screen;
    }

    public void start(){
        currentScreen.action();
        char input;
        while(currentScreen instanceof TerminalScreen){
            input = scanner.nextLine().charAt(0);
            TerminalAction action = ((TerminalScreen) currentScreen).getAction(input);
            if(action != null){
                currentScreen = action;
            }
            currentScreen.action();
        }
    }

    private static void println(String s){
        System.out.println(s);
    }
}
