package me.quicktwix898.redditstatgrapher.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UIHandler {
    //static
    final static UIHandler INSTANCE = new UIHandler(MainScreen.getInstance());

    public static UIHandler getInstance() {
        return INSTANCE;
    }

    //instance
    final Scanner scanner = new Scanner(System.in);
    TerminalAction currentAction;
    final List<Character> choices = Collections.synchronizedList(new ArrayList<Character>());

    private UIHandler(TerminalScreen screen){
        currentAction = screen;
    }

    public void start(){
        currentAction.action();
        char input;
        while(currentAction instanceof TerminalScreen){
            input = scanner.nextLine().charAt(0);
            TerminalChoice choice = ((TerminalScreen) currentAction).getChoice(input);
            choices.add(choice.getString());
            if(choice != null){
                currentAction = choice.getScreen();
            }
            currentAction.action();
        }
        System.out.println("Executing your query...");
    }
}
