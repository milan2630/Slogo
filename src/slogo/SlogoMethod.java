package slogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlogoMethod implements Command{
    private String myCommands;
    private String name;
    private int numArguments;
    private List<IntegerVariable> arguments;


    public SlogoMethod(String name, String commands, List<String> parNames){
        myCommands=commands;
        this.name = name;
        numArguments = parNames.size();
        for(int i = 0; i < numArguments; i++){
            arguments.add(new IntegerVariable(parNames.get(i), 0)); //TODO error checking
        }
    }

    public SlogoMethod(String name, List<String> parNames){
        this(name, new String(), parNames);
    }

    /**
     * adds Command object to List
     * @param command
     */
    public void addCommand(String command){
        myCommands = myCommands + " " + command;
    }

    /**
     * returns unmodifiable myCommands
     * @return unmodifiable List of myCommands
     */
    public String getCommands(){
        return myCommands;
    }


    /**
     * returns name of this Method object
     * @return name
     */
    public String getName(){
        return name;
    }

    @Override
    public int getNumArguments() {
        return numArguments;
    }

    @Override
    public void setArguments(List<Integer> args) {

    }

}
