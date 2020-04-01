package slogo.Model;

import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.TurtleModel.ImmutableTurtle;

import java.util.List;
import java.util.Map;

/**
 * Interface containing Backend methods that are meant for the Frontend to use
 */
public interface BackEndExternal {

    /**
     * Creates a list of ImmutableTurtles to execute in order based on the input from the console
     * @param input from the Console
     * @return a list of ImmutableTurtle states to execute
     */
    public Map<Double, List<ImmutableTurtle>> parseTurtleStatesFromCommands(String input) throws ParsingException;

    /**
     * Changes the parsing language to the given language as well as
     * converting all methods from previous language to new one
     * @param lang is the new Properties file name to implement
     */
    public void setLanguage(String lang);

    /**
     * Called to return the backend to the state before the previous command
     * @return the list of Immutable turtles returned by the previous command
     */
    public Map<Double, List<ImmutableTurtle>> undoAction();

}
