package slogo.Model.Parsing;

import slogo.Model.BackEndExternal;
import slogo.Model.Explorers.MethodExplorer;
import slogo.Model.Commands.Command;
import slogo.Model.Explorers.PaletteExplorer;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.Model.TurtleModel.Turtle;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.view.Visualizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements BackEndExternal {
    private static final String EXECUTE_COMMAND_METHOD_NAME = "executeCommand";

    private Visualizer frontend;
    private MethodExplorer methodExplorer;
    private VariableExplorer variableExplorer;
    private PaletteExplorer paletteExplorer;
    private List<ImmutableTurtle> internalStates;
    private List<Turtle> turtles;
    private String language;
    private List<ImmutableTurtle> previousInternalStates;
    private List<Turtle> previousTurtles;


    public CommandManager(Visualizer v, MethodExplorer me, VariableExplorer ve, PaletteExplorer pe, String lang){
        language = lang;
        frontend = v;
        methodExplorer = me;
        variableExplorer = ve;
        paletteExplorer = pe;
        internalStates = new ArrayList<>();
        Turtle startTurtle = new Turtle(1);
        turtles = new ArrayList<>();
        turtles.add(startTurtle);
        previousInternalStates = new ArrayList<>();
        previousTurtles = new ArrayList<>();
    }

    @Override
    public List<ImmutableTurtle> parseTurtleStatesFromCommands(String input) throws ParsingException {
        clearInternalStates();
        parseCommands(input);


        List<ImmutableTurtle> ret = getInternalStates();
        System.out.println("States:");
        for(ImmutableTurtle t: ret){
            System.out.println(t.getY());
        }
        backupTurtleList();
        backupInternalStateList();

        return getInternalStates();
    }

    @Override
    public List<ImmutableTurtle> undoAction(){
        turtles = previousTurtles;
        previousTurtles = new ArrayList<>();
        return previousInternalStates;
    }

    private void backupInternalStateList() {
        previousInternalStates = new ArrayList<>();
        for(ImmutableTurtle turtle: internalStates){
            previousInternalStates.add(new Turtle(turtle));
        }
    }

    private void backupTurtleList() {
        previousTurtles = new ArrayList<>();
        for(Turtle turtle: turtles){
            previousTurtles.add(new Turtle(turtle));
        }
    }

    public double parseCommands(String input) throws ParsingException {
        Parser parser = new Parser(this);
        return parser.parseCommands(input);
    }




    protected double actOnCommand(Command command, List<String> params) throws ParsingException {
        try {
            double ret = 0;
            for(Turtle turtle: turtles){
                if(turtle.isActive() == 1){
                    Method method = command.getClass().getDeclaredMethod(EXECUTE_COMMAND_METHOD_NAME, CommandManager.class, Turtle.class, List.class);
                    ret = (double) method.invoke(command, this, turtle, params);
                }
            }
            return ret;
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new ParsingException("ExecuteMissing", command.toString());
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof ParsingException){
                throw (ParsingException) e.getCause();
            }
            throw new ParsingException("CommandExecuteError", command.toString());
        }
    }

    public List<ImmutableTurtle> getInternalStates() {
        return internalStates;
    }

    private void clearInternalStates(){
        internalStates = new ArrayList<>();
    }

    public MethodExplorer getMethodExplorer() {
        return methodExplorer;
    }

    public VariableExplorer getVariableExplorer(){
        return variableExplorer;
    }

    public PaletteExplorer getPaletteExplorer(){
        return paletteExplorer;
    }

    public Visualizer getDisplay(){
        return frontend;
    }

    public List<Turtle> getTurtles(){
        return turtles;
    }

    @Override
    public void setLanguage(String lang){
        methodExplorer.convertLanguage(lang);
        language = lang;
    }

    public String getLanguage(){
        return language;
    }

}
