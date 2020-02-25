package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Parser {


    private String language;
    private CommandFactory factory;
    private Stack<IntegerVariable> executionTimesStack;
    private IntegerVariable executionTimes;
    private int executionLimit;
    private boolean creatingMethod;
    private MethodExplorer methodExplorer;
    private int lastReturnFromMethod;
    private SlogoMethod currentMethod;
    //private int bracketsSeen;

    public Parser(String lang, MethodExplorer me){
        language = lang;
        methodExplorer = me;
        factory = new CommandFactory(lang, methodExplorer);
        executionTimesStack = new Stack<>();
        executionTimes = new IntegerVariable("*1", 1);
        executionLimit = 1;
        creatingMethod = false;
        lastReturnFromMethod = 0;
        //bracketsSeen = 0;
    }

    /**
     * Creates a list of commands to execute in order based on the input from the console
     * @param input from the Console
     * @return a list of commands to execute
     */
    public List<ImmutableTurtle> parseStringToCommands(String input, Turtle turtle) throws ParsingException{
        List<ImmutableTurtle> stateList = new ArrayList<>();
        List<String> entityList = getEntitiesFromString(input);
        for(String entity: entityList){
            System.out.println(entity);
        }
        //stateList.addAll(parseLine(entityList, turtle));
        /*for(int i = 0; i < lineList.length; i++){
            if(lineList[i].indexOf("to") == 0){
                currentMethod = createMethod(lineList, i);
                i++;
                bracketsSeen++;
            }
            else if(creatingMethod){
                i = addLinesToMethod(lineList, i);
            }
            else{
                stateList.addAll(parseLine(lineList[i], turtle));
            }
        }*/

        return stateList;
    }


    private List<String> getEntitiesFromString(String input){
        String noCommentString = removeComments(input);
        String[] entities = noCommentString.split(" ");
        return parseEntities(entities);
    }

    private List<String> parseEntities(String[] entities) {
        List<String> entityList = new ArrayList<>();
        for(int i = 0; i < entities.length; i++){
            if(entities[i].equals("[")){
                i++;
                int bracketsSeen = 1;
                String item = "";
                while(bracketsSeen != 0){
                    if(entities[i].contains("]")){
                        bracketsSeen--;
                    }
                    else{
                        if(entities[i].contains("[")){
                            bracketsSeen++;
                        }
                        if(item.equals("")){
                            item = entities[i];
                        }
                        else{
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
            if(lineList[i].indexOf("#") != 0){
                noComments.add(lineList[i]);
            }
        }
        String[] noCommentArray = noComments.toArray(new String[0]);
        String noCommentString = String.join(" ", noCommentArray);
        return noCommentString;
    }


    private List<ImmutableTurtle> parseLine(List<String> entityList, Turtle turtle) {
        List<ImmutableTurtle> states = new ArrayList<>();

        Stack<Object> argumentStack = new Stack<>();
        Stack<Command> commandStack = new Stack<>();

        for(String item: entityList){
            try{
                Double arg = Double.parseDouble(item);
                argumentStack.push(arg);
            }
            catch (NumberFormatException e){
                if(item.indexOf(":") == 0){
                    argumentStack.push(item);
                }
                else {
                    pushCommand(commandStack, item);
                }
            }
            combineCommandsArgs(states, argumentStack, commandStack, turtle);
        }

        return states;

    }

    private void pushCommand(Stack<Command> commandStack, String item) {
        try {
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

    private void combineCommandsArgs(List<ImmutableTurtle> states, Stack<Object> argumentStack, Stack<Command> commandStack, Turtle turtle) {
        int numArguments = argumentStack.size();
        try {
            Command topCom = commandStack.peek();
            while(numArguments >= topCom.getNumArguments()){
                topCom = commandStack.pop();
                List<Object> params = new ArrayList<>();
                for(int i = 0; i < numArguments; i++){
                    params.add(argumentStack.pop());
                }
                Collections.reverse(params);
                //topCom.setArguments(params);
                double result = 0;
                result = turtle.actOnCommand(topCom, params);
                states.add(turtle.getImmutableTurtle());
                /*if(topCom instanceof SlogoMethod){
                    Parser internalParser = new Parser(language, methodExplorer);
                    states.addAll(internalParser.parseStringToCommands(((SlogoMethod) topCom).getCommandsAsString(), turtle));
                    result = lastReturnFromMethod;
                }
                else if(topCom instanceof ControlCommand){
                    //if bracket argument
                    // if no bracket argument: pass to command actor and set variables to desired, create method, run method
                }
                else {
                    result = turtle.actOnCommand(topCom);
                    states.add(turtle.getImmutableTurtle());
                }*/
                if(commandStack.size() > 0) {
                    argumentStack.add(result);
                    numArguments = argumentStack.size();
                    topCom = commandStack.peek();
                }
                else{
                    break;
                }
            }
        }
        catch(EmptyStackException | ParsingException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MethodExplorer me = new MethodExplorer();
        VariableExplorer ve = new VariableExplorer();
        Parser t = new Parser("English", me);
        Turtle turt = new Turtle(me, ve);

        /*SlogoMethod m = new SlogoMethod("NewMeth", new ArrayList<>(), new ArrayList<>());

        m.addCommand("fd 5");
        m.addCommand("fd fd 10");

        me.addMethod(m);*/
        //String s = "to NewMeth [ ]\n[\nfd 5 fd 5\nfd fd 10\n]\nNewMeth";
        //String s = "make :hi 32.1\nfd fd :hi";
        String s = "to NewMeth [ ]\n[\nfd 10\n]\nNewMeth";
        try {
            List<ImmutableTurtle> x = t.parseStringToCommands(s, turt);
            for(ImmutableTurtle c: x){
                //System.out.println(c.getX());
            }

        } catch (ParsingException e) {
            e.printStackTrace();
        }

    }
}
