package edu.touro.mco152.bm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * simple command executor
 */
public class Executor {

    private final Queue<ICommand> commands = new LinkedList();

    public boolean addCommand(ICommand c){
        return commands.add(c);
    }

    public void executeCommands(){
        for (ICommand c : commands) {
            c.execute();
        }
    }

}
