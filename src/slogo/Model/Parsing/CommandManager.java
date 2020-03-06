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
    private LanguageConverter languageConverter;


    public CommandManager(Visualizer v, MethodExplorer me, VariableExplorer ve, PaletteExplorer pe, LanguageConverter lang){
        languageConverter = lang;
        frontend = v;
        methodExplorer = me;
        variableExplorer = ve;
        paletteExplorer = pe;
        internalStates = new ArrayList<>();
        Turtle startTurtle = new Turtle(1);
        turtles = new ArrayList<>();
        turtles.add(startTurtle);
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


        return getInternalStates();
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
    }

    public LanguageConverter getLanguageConverter(){
        return languageConverter;
    }

}
