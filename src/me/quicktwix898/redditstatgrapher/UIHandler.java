package me.quicktwix898.redditstatgrapher;

import me.quicktwix898.redditstatgrapher.ui.MainScreen;
import me.quicktwix898.redditstatgrapher.ui.TerminalAction;
import me.quicktwix898.redditstatgrapher.ui.TerminalChoice;
import me.quicktwix898.redditstatgrapher.ui.TerminalScreen;

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
        currentAction.action();
        while(currentAction != null && currentAction instanceof TerminalScreen){
            String temp = scanner.nextLine();
            input = temp.length() < 1 ? "" : temp.substring(0, 1);
            TerminalChoice choice = ((TerminalScreen) currentAction).getChoice(input);
            if(choice != null){
                choices.add(choice.getString());
                currentAction = choice.getAction();
            }else{
                System.out.println(((TerminalScreen) currentAction).error());
            }
        }
        System.out.println("Executing your query...");
    }

    public static void main(String[] args) {
        INSTANCE.start();
    }
}
