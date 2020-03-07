package slogo.Model.Parsing;

import slogo.Model.Commands.Command;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.TurtleModel.Turtle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * Calls the command class to act on a given command
 */
public class CommandActor {

    private static final String EXECUTE_COMMAND_METHOD_NAME = "executeCommand";

    /**
     * Calls the execute method in the given command class using the given parameters and turtle
     * @param command the Command to execute
     * @param params the parameters to provide that command
     * @param currentTurtle the turtle to execute the commands on
     * @return the return value of the command
     * @throws ParsingException
     */
    public double actOnCommand(Command command, List<String> params, Turtle currentTurtle) throws ParsingException {
        double ret = 0;
        try {
            if(currentTurtle.isActive() == 1.0) {
                Method method = command.getClass().getDeclaredMethod(EXECUTE_COMMAND_METHOD_NAME, CommandManager.class, Turtle.class, List.class);
                ret = (double) method.invoke(command, this, currentTurtle, params);
            }
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new ParsingException("ExecuteMissing", command.toString());
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof ParsingException){
                throw (ParsingException) e.getCause();
            }
            throw new ParsingException("CommandExecuteError", command.toString());
        }
        return ret;
    }
}
