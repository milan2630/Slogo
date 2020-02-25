package slogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlogoMethod implements Command{
    private List<String> myCommands;
    private String name;
    private int numArguments;
    private List<IntegerVariable> arguments;


    public SlogoMethod(String name, List<String> commands, List<String> parNames){
        myCommands=commands;
        this.name = name;
        numArguments = parNames.size();
        for(int i = 0; i < numArguments; i++){
            arguments.add(new IntegerVariable(parNames.get(i), 0)); //TODO error checking
        }
    }

    public SlogoMethod(String name, List<String> parNames){
        this(name, new ArrayList<>(), parNames);
    }

    /**
     * adds Command object to List
     * @param command
     */
    public void addCommand(String command){
        myCommands.add(command);
    }

    /**
     * returns unmodifiable myCommands
     * @return unmodifiable List of myCommands
     */
    public List<String> getCommandList(){
        return Collections.unmodifiableList(myCommands);
    }

    public String getCommandsAsString(){
        String ret = "";
        for(String com: myCommands){
            ret = ret + com + "\n";
        }
        return ret;
    }

    /**
     * deletes first occurence of Command delete in myCommands
     * @param delete
     */
    public void deleteCommand(Command delete){
        myCommands.remove(delete);
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
        for(int i = 0; i < args.size(); i++) {
            IntegerVariable currentArg = arguments.get(i);
            currentArg.setValue(args.get(i));
            String initializeVar = "make " + currentArg.getName() + " " + currentArg.getValue();
            myCommands.add(0, initializeVar);
        }

    }

}
