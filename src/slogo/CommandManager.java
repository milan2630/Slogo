package slogo;

import slogo.Commands.Command;
import view.Visualizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class CommandManager {
    private static final String EXECUTE_COMMAND_METHOD_NAME = "executeCommand";

    private Visualizer frontend;
    private MethodExplorer methodExplorer;
    private VariableExplorer variableExplorer;
    private List<ImmutableTurtle> internalStates;
    private List<Turtle> turtles;
    private String language;


    public CommandManager(Visualizer v, MethodExplorer me, VariableExplorer ve, String lang){
        language = lang;
        frontend = v;
        methodExplorer = me;
        variableExplorer = ve;
        internalStates = new ArrayList<>();
        Turtle startTurtle = new Turtle(methodExplorer, variableExplorer, lang, 1, true);
        turtles = new ArrayList<>();
        turtles.add(startTurtle);
    }


    public double actOnCommand(Command command, List<String> params) throws ParsingException {
        return callMethod(command, params);
    }

    private double callMethod(Command command, List<String> params) throws ParsingException {
        try {
            double ret = 0;
            for(Turtle turtle: turtles){
                if(turtle.isActive()){
                    Method method = command.getClass().getDeclaredMethod(EXECUTE_COMMAND_METHOD_NAME, CommandManager.class, Turtle.class, List.class);
                    ret = (double) method.invoke(command, this, turtle, params);
                }
            }
            //internalStates.add(myTurtle.getImmutableTurtle());
            return ret;
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new ParsingException("ExecuteMissing", command.toString());
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof ParsingException){
                throw (ParsingException) e.getCause();
            }
            throw new ParsingException("CommandExecuteError", command.toString());//TODO implemenet Command.toString()
        }
    }

    public List<ImmutableTurtle> getInternalStates() {
        return internalStates;
    }

    public void clearInternalStates(){
        internalStates = new ArrayList<>();
    }

    public MethodExplorer getMethodExplorer() {
        return methodExplorer;
    }

    public VariableExplorer getVariableExplorer(){
        return variableExplorer;
    }

    public List<Turtle> getTurtles(){
        return turtles;
    }

    public String getLanguage(){
        return language;
    }

}
