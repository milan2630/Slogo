package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Parser {



    private CommandFactory factory;


    public Parser(String lang){
        factory = new CommandFactory(lang);
    }

    /**
     * Creates a list of commands to execute in order based on the input from the console
     * @param input from the Console
     * @return a list of commands to execute
     */
    public List<Command> parseStringToCommands(String input) throws ParsingException{
        List<Command> commandList = new ArrayList<>();
        String[] lineList = input.split("\n");
        for(int i = 0; i < lineList.length; i++){
            commandList.addAll(parseLine(lineList[i]));
        }
        return commandList;
    }

    private List<Command> parseLine(String line) {
        List<Command> commands = new ArrayList<>();




    }


}
