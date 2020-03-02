package slogo;

import slogo.Commands.Command;

import java.util.List;

/**
 * Interface containing Backend methods that are meant for the Frontend to use
 */
public interface BackEndExternal {

    /**
     * Creates a list of ImmutableTurtles to execute in order based on the input from the console
     * @param input from the Console
     * @return a list of ImmutableTurtle states to execute
     */
    public List<ImmutableTurtle> parseCommands(String input) throws ParsingException;

    public void setLanguage(String lang);

}
