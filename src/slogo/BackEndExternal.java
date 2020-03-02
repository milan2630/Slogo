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

   /* *//**
     * Makes the appropriate changes to the backend version of the Turtle, Pen, and Trail based
     * on a command
     * @param command the command to act on
     * @throws SlogoRuntimeException if the command cannot be executed
     *//*
    public void actOnCommand(Command command) throws SlogoRuntimeException;

    *//**
     * Used by the frontend to determine new turtle location
     * @return the x coordinate of the Turtle after a command
     *//*
    public double getTurtleX();

    *//**
     * Used by the frontend to determine new turtle location
     * @return the y coordinate of the Turtle after a command
     *//*
    public double getTutleY();

    *//**
     * Used by the frontend to determine new turtle heading
     * @return the heading of the Turtle after a command
     *//*
    public double getTurtleHeading();

    *//**
     * Used by the frontend to determine if a Trail should be made
     * @return the heading of the Turtle after a command
     *//*
    public int getPenState();*/

}
