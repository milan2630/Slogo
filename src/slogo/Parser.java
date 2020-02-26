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
        List<String> entityList = getEntitiesFromString(input);
        return new ArrayList<>(parseEntityList(entityList, turtle));
    }


    private List<String> getEntitiesFromString(String input){
        String noCommentString = removeComments(input);
        String[] entities = noCommentString.split(" ");
        return combineBrackets(entities);
    }

    //TODO check for proper number of brackets
    private List<String> combineBrackets(String[] entities) {
        List<String> entityList = new ArrayList<>();
        for(int i = 0; i < entities.length; i++){
            if(entities[i].equals("[")){
                i++;
                int bracketsSeen = 1;
                String item = "";
                while(bracketsSeen != 0) {
                    if (entities[i].contains("]")) {
                        bracketsSeen--;
                    }
                    if (entities[i].contains("[")) {
                        bracketsSeen++;
                    }
                    if (bracketsSeen != 0) {
                        if (item.equals("")) {
                            item = entities[i];
                        }
                        else {
                            item = item + " " + entities[i];
                        }
                    }
                    i++;
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

        for(String item: entityList){
            if(factory.isCommand(item)){
                pushCommand(commandStack, item);
            }
            else{
                argumentStack.push(item);
            }
            combineCommandsArgs(states, argumentStack, commandStack, turtle);
        }
        return states;
    }

    private void pushCommand(Stack<Command> commandStack, String item) throws ParsingException {
        Command com = factory.getCommand(item);
        commandStack.push(com);
    }

    private void combineCommandsArgs(List<ImmutableTurtle> states, Stack<String> argumentStack, Stack<Command> commandStack, Turtle turtle) {
        int numArguments = argumentStack.size();
        try {
            Command topCom = commandStack.peek();
            while(numArguments >= topCom.getNumArguments()){
                topCom = commandStack.pop();
                List<String> params = new ArrayList<>();
                for(int i = 0; i < numArguments; i++){
                    params.add(argumentStack.pop());
                }
                Collections.reverse(params);
                String result = turtle.actOnCommand(topCom, params);
                states.addAll(turtle.getInternalStates());
                if(commandStack.size() > 0) {
                    argumentStack.add(result);
                    numArguments = argumentStack.size();
                    topCom = commandStack.peek();
                }
                else{
                    finalReturn = Double.parseDouble(result);
                    break;
                }
            }
        }
        catch(EmptyStackException | ParsingException e){
            e.printStackTrace();
        }
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
