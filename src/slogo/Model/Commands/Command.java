package slogo.Model.Commands;

import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.TurtleModel.Turtle;

import java.util.List;

/**
 * Interface representing a class that can perform an action given parameters
 */
public interface Command {

    /**
     * @return the number of arguments this Command takes
     */
    public int getNumArguments();

    /**
     * Performs an action based on the given parameters
     * @param commandManager holds information about explorers and parser
     * @param myTurtle the turtle to execute the command on
     * @param params the parameters of the command
     * @return the return value of the executed slogo command
     * @throws ParsingException
     */
    public abstract double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException;
}
