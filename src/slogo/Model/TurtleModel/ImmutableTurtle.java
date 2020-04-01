package slogo.Model.TurtleModel;

/**
 * Interface for a turtle that cannot have its attributes changed
 */
public interface ImmutableTurtle {

    /**
     * @return x coordinate
     */
    public double getX();

    /**
     * @return y coordinate
     */
    public double getY();

    /**
     * @return heading
     */
    public double getHeading();

    /**
     * @return pen status
     */
    public double getPenState();

    /**
     * @return showing status
     */
    public double getShowing();

    /**
     * @return whether the turtle is active or not
     */
    public double isActive();

    /**
     * @return the pen color index
     */
    public double getPenColorIndex();

    /**
     * @return the pen thickness in pixels
     */
    public double getPenThickness();

    /**
     * @return the turtle image index
     */
    public double getTurtleImageIndex();

    /**
     * @return the turtle ID
     */
    public double getID();
}
