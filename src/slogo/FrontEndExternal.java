package slogo;

import java.util.List;

/**
 * Interface containing Frontend methods that are meant for the Backend to use
 */
public interface FrontEndExternal {

  /**
   * Updates the turtle display using a list of all the states of a turtle
   *
   * @param turtleList a list of turtle states from the backend
   */
  public void updateTurtle(List<ImmutableTurtle> turtleList);

  /**
   * A getter for the current turtle state in case an error is thrown while executing a program
   *
   * @return the current state before an error occurred
   */
  public ImmutableTurtle getCurrentTurtle();

  /**
   * @return the language that was set by the user
   */
  public String getLanguage();

  /**
   * Handle an error and tell the User what issue occurred
   *
   * @param error the error that was thrown in the backend
   */
  public void displayError(Exception error);

}
