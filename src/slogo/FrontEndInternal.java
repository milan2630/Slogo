package slogo;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.beans.EventHandler;


/**
 * Interface containing methods that are intended to be used between different components of the Frontend
 */
public interface FrontEndInternal {
    /**
     * Update the view based on the changes in the TurtleView object
     * @param turtle is the changed TurtleView
     */
    public void updateTurtleDisplay(TurtleView turtle);

    /**
     * Update the view based on the changes in the PenView object
     * @param pen is the changed PenView
     */
    public void updatePenDisplay(PenView pen);

    /**
     * Update the view based on the changes in the TrailView object
     * @param trail is the changed TrailView
     */
    public void updateTrailDisplay(TrailView trail);


    /**
     * Set the background color based on the UI
     * @param color is the color to change to
     */
    public void setBackgroundColor(Color color);

    /**
     * Set the image of the Turtle to a new image
     * @param image the new image to represent the Turtle
     */
    public void setTurtleImage(Image image);

    /**
     * Set the image of the Pen to a new image
     * @param image the new image to represent the Pen
     */
    public void setPenImage(Image image);

    /**
     * Set the trail color based on the UI
     * @param color is the color to change to
     */
    public void setTrailColor(Color color);

    /**
     * Displays an error that occurred in the frontend
     * @param error the error that occurred
     */
    public void displayError(Exception error);

    /**
     * Create a button that changes the frontend
     * @param event is the desired action on the button click
     * @param property is the key in the property file for the desired text on the button
     */
    public Button createButton(EventHandler event, String property);

}
