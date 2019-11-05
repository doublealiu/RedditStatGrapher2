package me.quicktwix898.redditstatgrapher;

import me.quicktwix898.redditstatgrapher.ui.*;

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
    final List<Object> choices = Collections.synchronizedList(new ArrayList<>());

    private UIHandler(TerminalScreen screen){
        currentAction = screen;
    }

    public List<Object> start(){
        String input;
        currentAction.action();
        while(currentAction != null && currentAction instanceof TerminalScreen){
            String temp = scanner.nextLine();
            input = temp.length() < 1 ? "" : temp.substring(0, 1);
            TerminalChoice choice;
            if(currentAction instanceof SubNameScreen || currentAction instanceof PostIDScreen) {
                choice = ((TerminalScreen) currentAction).getChoice(temp);
            }else{
                choice = ((TerminalScreen) currentAction).getChoice(input);
            }
            if(choice != null){
                choices.add(choice.getString());
                currentAction = choice.getAction();
                if(currentAction == null){
                    break;
                }
                currentAction.action();
            }else{
                if(currentAction instanceof FileNameScreen){
                    choices.add("");
                    break;
                }else {
                    System.out.println(((TerminalScreen) currentAction).error());
                }
            }
        }
        for(Object o : choices){
            System.out.println(o.toString());
        }
        return choices;
    }
}
