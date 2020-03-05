package slogo.Model.Parsing;

import slogo.Model.Commands.Command;
import slogo.Model.ErrorHandling.ParsingException;

import java.util.*;

public class Parser{
    private CommandFactory factory;
    private CommandManager commandManager;
    private int indexInEntityList;
    public Parser(CommandManager cm){
        factory = new CommandFactory(cm.getLanguage(), cm.getMethodExplorer());
        commandManager = cm;
        indexInEntityList = 0;
    }

    /**
     * Creates a list of commands to execute in order based on the input from the console
     * @param input from the Console
     */
    public double parseCommands(String input) throws ParsingException {
//        System.out.println("AAA");
//        System.out.println(input);
//        System.out.println("AAA");
        if(input == null || input.length() == 0){
            return 0;
        }
        input = input.toLowerCase();
        input = stripBrackets(input);
        List<String> entityList = getEntitiesFromString(input);
        return parseEntityList(entityList);
    }

    private String stripBrackets(String input) {
        if(input.charAt(0) == '[' && input.charAt(input.length()-1) == ']'){
            return input.substring(1, input.length()-1);
        }
        return input;
    }


    private List<String> getEntitiesFromString(String input) throws ParsingException {
        String noCommentString = removeComments(input);
        if(noCommentString.equals("")){
            return new ArrayList<>();
        }
        noCommentString = noCommentString.strip();
        noCommentString = noCommentString.replaceAll("\\s+", " ");
        String[] entities = noCommentString.split(" ");
        return Arrays.asList(entities);
    }

    private String removeComments(String input) {
        String[] lineList = input.split("\n");
        List<String> noComments = new ArrayList<>();
        for (String s : lineList) {
            if (s.indexOf("#") != 0 && !s.equals("")) {
                noComments.add(s);
            }
        }
        String[] noCommentArray = noComments.toArray(new String[0]);
        return String.join(" ", noCommentArray);
    }

    private double parseEntityList(List<String> entityList) throws ParsingException {

        Stack<String> argumentStack = new Stack<>();
        Stack<Command> commandStack = new Stack<>();
        Stack<Integer> countFromStack = new Stack<>();
        Iterator<String> entityIterator = new EntityListIterator(entityList);
        double ret = 0;
        while(entityIterator.hasNext()){
            try {
                String item = entityIterator.next();
                if(factory.isCommand(item)){
                    pushCommand(commandStack, item);
                    //System.out.println("OnCommand: " + item);
                    countFromStack.push(argumentStack.size());
                }
                else{
                    argumentStack.push(item);
                    //System.out.println("OnArgs: " + item);
                }
            }
            catch (ArrayIndexOutOfBoundsException e){
                throw new ParsingException("MissingCloseBracket");
            }

            ret = combineCommandsArgs(argumentStack, commandStack, countFromStack);
        }
        checkUnfulfilledCommands(commandStack);
        return ret;
    }
    
    private void checkUnfulfilledCommands(Stack<Command> commandStack) throws ParsingException {
        if(commandStack.size() > 0){
            String unfulfilled = "";
            while(commandStack.size() > 0){
                unfulfilled = unfulfilled + commandStack.pop();
            }
            throw new ParsingException("UnfulfilledCommands", unfulfilled);
        }
    }

    private void pushCommand(Stack<Command> commandStack, String item) throws ParsingException {
        Command com = factory.getCommand(item);
        commandStack.push(com);
    }

    private double combineCommandsArgs(Stack<String> argumentStack, Stack<Command> commandStack, Stack<Integer> countFromStack) throws ParsingException {
        int numArguments = argumentStack.size();
        try {
            Command topCom = commandStack.peek();
            while(numArguments - countFromStack.peek() >= topCom.getNumArguments()){
                topCom = commandStack.pop();
                //System.out.println("OffCommand: " + topCom.toString());
                List<String> params = new ArrayList<>();
                for(int i = 0; i < numArguments - countFromStack.peek(); i++){
                    String s = argumentStack.pop();
                    //System.out.println("OffArgs: " + s);
                    params.add(s);
                    //params.add(argumentStack.pop());

                }
                Collections.reverse(params);
                String result = commandManager.actOnCommand(topCom, params) + "";

                //commandManager.clearInternalStates();
                if(commandStack.size() > 0) {
                    argumentStack.add(result);
                    //System.out.println("OnArgs: " + result);
                    countFromStack.pop();
                    numArguments = argumentStack.size();
                    topCom = commandStack.peek();
                }
                else{
                    return Double.parseDouble(result);
                }
            }
        }
        catch(EmptyStackException e){
            throw new ParsingException("UnrecognizedEntity", argumentStack.peek());
        }
        return 0;
    }
}
