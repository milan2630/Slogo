package slogo.Model;


import slogo.Model.Commands.Command;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.MethodExplorer;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.SlogoRuntimeException;

import java.util.List;

/**
 * Interface containing methods that are intended to be used between different components of the Backend
 */
public interface BackEndInternal {

    /**
     * Parse a set of commands from a string and return the double that the last command returns
     * @param input String to parse
     * @return the last return from the commands
     * @throws ParsingException if there is an issue with the parsing string
     */
    public double parseCommands(String input) throws ParsingException;

    /**
     * Act on a Command by calling the Command's executeCommand method
     * @param command the command to execute
     * @param params list of parameters
     * @return the double return value of the last command
     * @throws ParsingException if there is an issue with executing the command, likely that the parameters are not valid
     */
    public double actOnCommand(Command command, List<String> params) throws ParsingException;

    /**
     * @return list of immutable turtles that was created during parsing
     */
    public List<ImmutableTurtle> getInternalStates();

    /**
     * @return the method explorer to access methods
     */
    public MethodExplorer getMethodExplorer();

    /**
     * @return Variable Explorer to access variables
     */
    public VariableExplorer getVariableExplorer();

    /**
     * @return the current language of the backend
     */
    public String getLanguage();
}
