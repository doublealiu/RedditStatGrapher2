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
    final List<String> choices = Collections.synchronizedList(new ArrayList<>());

    private UIHandler(TerminalScreen screen){
        currentAction = screen;
    }

    public void start(){
        String input;
        while(currentAction instanceof TerminalScreen){
            currentAction.action();
            input = scanner.nextLine().substring(0, 1);
            TerminalChoice choice = ((TerminalScreen) currentAction).getChoice(input);
            if(choice != null){
                choices.add(choice.getString());
                currentAction = choice.getScreen();
            }
        }
        System.out.println("Executing your query...");
    }
}
