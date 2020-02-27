package slogo;

import slogo.Commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Parser {
    private CommandFactory factory;
    private double finalReturn;

    public Parser(String lang, MethodExplorer me){
        factory = new CommandFactory(lang, me);
        finalReturn = 0;
    }

    /**
     * Creates a list of commands to execute in order based on the input from the console
     * @param input from the Console
     * @return a list of commands to execute
     */
    public List<ImmutableTurtle> parseCommands(String input, Turtle turtle) throws ParsingException{
        input = input.toLowerCase();
        List<String> entityList = getEntitiesFromString(input);
        return parseEntityList(entityList, turtle);
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
                String item = "";
                try {
                    while (bracketsSeen != 0) {
                        if (entities[i].contains("]")) {
                            bracketsSeen--;
                        }
                        if (entities[i].contains("[")) {
                            bracketsSeen++;
                        }
                        if (bracketsSeen != 0) {
                            if (item.equals("")) {
                                item = entities[i];
                            } else {
                                item = item + " " + entities[i];
                            }
                        }
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

    private List<ImmutableTurtle> parseEntityList(List<String> entityList, Turtle turtle) throws ParsingException {
        List<ImmutableTurtle> states = new ArrayList<>();

        Stack<String> argumentStack = new Stack<>();
        Stack<Command> commandStack = new Stack<>();
        int reset = 0;
        for(String item: entityList){
            if(factory.isCommand(item)){
                pushCommand(commandStack, item);
                reset = argumentStack.size();
            }
            else{
                argumentStack.push(item);
            }
            reset = combineCommandsArgs(states, argumentStack, commandStack, turtle, reset);
        }
        return states;
    }

    private void pushCommand(Stack<Command> commandStack, String item) throws ParsingException {
        Command com = factory.getCommand(item);
        commandStack.push(com);
    }

    private int combineCommandsArgs(List<ImmutableTurtle> states, Stack<String> argumentStack, Stack<Command> commandStack, Turtle turtle, int reset) throws ParsingException {
        int numArguments = argumentStack.size();
        try {
            Command topCom = commandStack.peek();
            while(numArguments - reset >= topCom.getNumArguments()){
                topCom = commandStack.pop();
                List<String> params = new ArrayList<>();
                for(int i = 0; i < numArguments - reset; i++){
                    params.add(argumentStack.pop());
                }
                Collections.reverse(params);
                String result = turtle.actOnCommand(topCom, params);
                states.addAll(turtle.getInternalStates());
                if(commandStack.size() > 0) {
                    argumentStack.add(result);
                    reset--;
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
        return reset;
    }

    public double getFinalReturn(){
        return finalReturn;
    }

    public static void main(String[] args) throws ParsingException {



        /*MethodExplorer me = new MethodExplorer();
        VariableExplorer ve = new VariableExplorer();
        Parser t = new Parser("English", me);
        Turtle turt = new Turtle(me, ve, "English");
        //String s = "to NewMeth [ :hi :h ]\n[\nfd :hi\nfd :h\n]\nNewMeth 6 2";
        //String s = "make :hello 3\nfd fd :hello";
        //String s = "repeat fd fd 3 [ fd 100 ]";
        //String s = "for [ :hi 10.5 15.5 .5 ] [ fd :hi ]";
        //String s = "if fd fd 5 [ fd 10 ]";
        //String s = "ifelse .1 [ fd 5 ] [ fd 10 ]";
        //String s = "to NewMeth [ ]\n[\nfd 5 fd 5\nfd fd 10\n]\nNewMeth";
        //String s = "make :hello 3\nfd fd :hello";
        String s = "to NewMeth [ ]\n[ fd 5 ]\nNewMeth";
        try {
            List<ImmutableTurtle> x = t.parseCommands(s, turt);
            for(ImmutableTurtle c: x){
                System.out.println(c.getX());
                //System.out.println(c.getHeading());
            }

        } catch (ParsingException e) {
            e.printStackTrace();
        }
*/
    }
}
