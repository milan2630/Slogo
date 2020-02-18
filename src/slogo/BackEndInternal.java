package slogo;


import java.util.List;

/**
 * Interface containing methods that are intended to be used between different components of the Backend
 */
public interface BackEndInternal {

    /**
     * Update the Turtle based on a given command
     * @param command is the command to use as instructions
     */
    public void updateTurtle(Command command);

    /**
     * Update the Pen based on a given command
     * @param command is the command to use as instructions
     */
    public void updatePen(Command command);

    /**
     * Update the Trail based on a given command
     * @param command is the command to use as instructions
     */
    public void updateTrail(Command command);

    /**
     * Create a Method from a list of Commands
     * A Methdod is a group of commands that can be called once and executed in order
     * @param commandList
     * @return a Method with the group of commands
     */
    public Method createMethod(List<Command> commandList);

    /**
     *Update the history that contains past actions
     */
    public void updateHistory();

}
