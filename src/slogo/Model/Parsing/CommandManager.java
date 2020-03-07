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
import java.util.List;
import java.util.Map;

/**
 * Implements the BackEndExternal API and handles the flow of information in the backend
 */
public class CommandManager implements BackEndExternal {


    private Visualizer frontend;
    private MethodExplorer methodExplorer;
    private VariableExplorer variableExplorer;
    private PaletteExplorer paletteExplorer;
    private TurtleModelManager turtleManager;
    private LanguageConverter languageConverter;
    private Turtle currentTurtle;
    private CommandActor commandActor;


    /**
     * Create a Command Manager with given explorers
     * @param v Visualizer
     * @param me MethodExplorer
     * @param ve VariableExplorer
     * @param pe PaletteExplorer
     * @param lang properties file to parse with
     */
    public CommandManager(Visualizer v, MethodExplorer me, VariableExplorer ve, PaletteExplorer pe, LanguageConverter lang){
        languageConverter = lang;
        frontend = v;
        methodExplorer = me;
        variableExplorer = ve;
        paletteExplorer = pe;
        turtleManager = new TurtleModelManager();
        currentTurtle = new Turtle(1);
        commandActor = new CommandActor();

    }

    @Override
    public Map<Double, List<ImmutableTurtle>> parseTurtleStatesFromCommands(String input) throws ParsingException {
        clearInternalStates();
        parseCommands(input);
        Map<Double, List<ImmutableTurtle>> retMap = getInternalStates();
        turtleManager.backupTurtleList();
        turtleManager.backupInternalStateList();
        return retMap;
    }

    @Override
    public Map<Double, List<ImmutableTurtle>> undoAction(){
        return turtleManager.undoAction();
    }


    /**
     * Loops through turtles and parses the commands for each
     * @param input is the string to parse
     * @return the last return of the given commands
     * @throws ParsingException
     */
    public double parseCommands(String input) throws ParsingException {
        TurtleIterator iterator = turtleManager.iterator();
        double ret = 0;
        while(iterator.hasNext()){
            currentTurtle = iterator.next();
            Parser parser = new Parser(this);
            ret = parser.parseCommands(input);
        }
        return ret;
    }


    /**
     * Calls on the same method in CommandActor
     * @param command the Command to execute
     * @param params the parameters to provide that command
     * @return the return value of the command
     * @throws ParsingException
     */
    protected double actOnCommand(Command command, List<String> params) throws ParsingException {
        return commandActor.actOnCommand(command, params, currentTurtle);
    }

    private Map<Double, List<ImmutableTurtle>> getInternalStates() {
        return turtleManager.getInternalStates();
    }

    private void clearInternalStates(){
        turtleManager.clearInternalStates();
    }

    /**
     * @return the methodExplorer
     */
    public MethodExplorer getMethodExplorer() {
        return methodExplorer;
    }

    /**
     * @return the VariableExplorer
     */
    public VariableExplorer getVariableExplorer(){
        return variableExplorer;
    }

    /**
     * @return the PaletteExplorer
     */
    public PaletteExplorer getPaletteExplorer(){
        return paletteExplorer;
    }

    /**
     * @return the Display
     */
    public Visualizer getDisplay(){
        return frontend;
    }

    /**
     * @return the TurtleManager of the backend
     */
    public TurtleModelManager getTurtleManager(){
        return turtleManager;
    }

    @Override
    public void setLanguage(String lang){
        methodExplorer.convertLanguage(lang);
    }

    /**
     * @return the LanguageConverter
     */
    public LanguageConverter getLanguageConverter(){
        return languageConverter;
    }

}
