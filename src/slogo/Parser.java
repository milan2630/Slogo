package slogo;

import slogo.Commands.Command;

import java.util.*;

public class Parser implements BackEndExternal{
    private CommandFactory factory;
    private CommandManager commandManager;
    private String language;
    private double finalReturn;
    public Parser(String lang, CommandManager cm){
        language = lang;
        finalReturn = 0;
        factory = new CommandFactory(language, cm.getMethodExplorer());
        commandManager = cm;
    }

    /**
     * Creates a list of commands to execute in order based on the input from the console
     * @param input from the Console
     * @return a list of commands to execute
     */
    public List<ImmutableTurtle> parseCommands(String input) throws ParsingException{
        //System.out.println("AAA");
        //System.out.println(input);
        //System.out.println("AAA");
        input = input.toLowerCase();
        List<String> entityList = getEntitiesFromString(input);
        return parseEntityList(entityList);
    }


    private List<String> getEntitiesFromString(String input) throws ParsingException {
        String noCommentString = removeComments(input);
        if(noCommentString.equals("")){
            return new ArrayList<>();
        }
        noCommentString = noCommentString.replaceAll("\\s+", " ");
        String[] entities = noCommentString.split(" ");
        return combineBrackets(entities);
    }

    private List<String> combineBrackets(String[] entities) throws ParsingException {
        List<String> entityList = new ArrayList<>();
        for(int i = 0; i < entities.length; i++){
            if(entities[i].equals("[")){

                i++;
                int bracketsSeen = 1;
                String item = "[";
                try {
                    while (bracketsSeen != 0) {
                        if (entities[i].contains("]")) {
                            bracketsSeen--;
                        }
                        if (entities[i].contains("[")) {
                            bracketsSeen++;
                        }
                        item = item + " " + entities[i];
                        i++;
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    throw new ParsingException("MissingCloseBracket");
                }
                i--;
                entityList.add(item);
            }
            else{
                entityList.add(entities[i]);
            }
        }
        return entityList;
    }

    private String removeComments(String input) {
        String[] lineList = input.split("\n");
        List<String> noComments = new ArrayList<>();
        for(int i = 0; i < lineList.length; i++){
            if(lineList[i].indexOf("#") != 0 && !lineList[i].equals("")){
                noComments.add(lineList[i]);
            }
        }
        String[] noCommentArray = noComments.toArray(new String[0]);
        return String.join(" ", noCommentArray);
    }

    private List<ImmutableTurtle> parseEntityList(List<String> entityList) throws ParsingException {
        List<ImmutableTurtle> states = new ArrayList<>();

        Stack<String> argumentStack = new Stack<>();
        Stack<Command> commandStack = new Stack<>();
        Stack<Integer> countFromStack = new Stack<>();
        for(String item: entityList){
            if(factory.isCommand(item)){
                pushCommand(commandStack, item);
                //System.out.println("OnCommand: " + item);
                countFromStack.push(argumentStack.size());
            }
            else{
                argumentStack.push(item);
                //System.out.println("OnArgs: " + item);
            }
            combineCommandsArgs(states, argumentStack, commandStack, countFromStack);
        }
        if(commandStack.size() > 0){
            String unfulfilled = "";
            while(commandStack.size() > 0){
                unfulfilled = unfulfilled + commandStack.pop();
            }
            throw new ParsingException("UnfulfilledCommands", unfulfilled);
        }
        return states;
    }

    private void pushCommand(Stack<Command> commandStack, String item) throws ParsingException {
        Command com = factory.getCommand(item);
        commandStack.push(com);
    }

    private void combineCommandsArgs(List<ImmutableTurtle> states, Stack<String> argumentStack, Stack<Command> commandStack, Stack<Integer> countFromStack) throws ParsingException {
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
                for(ImmutableTurtle t: commandManager.getInternalStates()){
                    System.out.println(t.getY());
                }
                String result = commandManager.actOnCommand(topCom, params) + "";
                for(ImmutableTurtle t: commandManager.getInternalStates()){
                    System.out.println(t.getY());
                }
                states.addAll(commandManager.getInternalStates());
                commandManager.clearInternalStates();
                if(commandStack.size() > 0) {
                    argumentStack.add(result);
                    //System.out.println("OnArgs: " + result);
                    countFromStack.pop();
                    numArguments = argumentStack.size();
                    topCom = commandStack.peek();
                }
                else{
                    finalReturn = Double.parseDouble(result);
                    break;
                }
            }
        }
        catch(EmptyStackException e){
            throw new ParsingException("UnrecognizedEntity", argumentStack.peek());
        }
    }

    public double getFinalReturn(){
        return finalReturn;
    }

    public void setLanguage(String lang){
        factory.setupLanguage(lang);
    }
}
