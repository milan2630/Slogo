package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

        Stack<Integer> argumentStack = new Stack<>();
        Stack<Command> commandStack = new Stack<>();

        String[] entityList = line.split(" ");
        for(String item: entityList){
            try{
                int arg = Integer.parseInt(item);
                argumentStack.push(arg);
            }
            catch (NumberFormatException e){
                try{
                    Command com = factory.getCommand(item);
                    commandStack.push(com);

                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

            int numArguments = argumentStack.size();
            Command topCom = commandStack.peek();
            while(numArguments >= topCom.getNumArguments()){
                topCom = commandStack.pop();
                List<Integer> params = new ArrayList<>();
                for(int i = 0; i < numArguments; i++){
                    params.add(argumentStack.pop());
                }
                Collections.reverse(params);
                topCom.setArguments(params);
                commands.add(topCom);
                argumentStack.add(topCom.getReturn());
                numArguments = argumentStack.size();
                if(commandStack.size() > 0) {
                    topCom = commandStack.peek();
                }
                else{
                    break;
                }
            }
        }

        return commands;

    }


    public static void main(String[] args) {
        Parser t = new Parser("English");
        String s = "fd fd 50";
        try {
            List<Command> x = t.parseStringToCommands(s);
            for(Command c: x){
                System.out.println(c.getClass() + ": " + c.getReturn());
            }

        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }
}
