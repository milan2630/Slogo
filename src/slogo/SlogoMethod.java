package slogo;

import java.util.Collections;
import java.util.List;

public class SlogoMethod {
    private List<Command> myCommands;
    private String name;
    public SlogoMethod(String name, List commands){
        myCommands=commands;
        this.name = name;
    }

    /**
     * adds Command object to List
     * @param command
     */
    public void addCommand(Command command){
        myCommands.add(command);
    }

    /**
     * returns unmodifiable myCommands
     * @return unmodifiable List of myCommands
     */
    public List getCommands(){
        return Collections.unmodifiableList(myCommands);
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
}
