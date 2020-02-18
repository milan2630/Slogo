package slogo;

import java.beans.EventHandler;

/**
 * Interface containing Frontend methods that are meant for the Backend to use
 */
public interface FrontEndExternal {

    /**
     * Accesses the Console to determine what the user typed in
     * @return a string containing what the user typed in the console before hitting run
     */
    public String getConsoleString();

    /**
     * Set the x and y of the turtle, pen, and trail
     * @param newX the new X position determined by the backend
     * @param newY the new Y position determined by the backend
     */
    public void updatePositions(double newX, double newY);


    /**
     * Set a new heading for the turtle
     * @param newHeading is the new direction the turtle should be facing determined by the backend
     */
    public void updateHeading(double newHeading);


    /**
     * Change whether the pen is down or not
     * @param penState is the new state to set the pen to
     */
    public void updatePenState(int penState);

    /**
     * @return the language that was set by the user
     */
    public String getLanguage();

    /**
     * Handle an error and tell the User what issue occurred
     * @param error the error that was thrown in the backend
     */
    public void displayError(Exception error);

    /**
     * Create a button that makes a backend change and add the button to the UI
     * @param event the desired action upon button click
     * @param property the key linked to a resource file that determines what text is on the button
     */
    public void createButton(EventHandler event, String property);


}
