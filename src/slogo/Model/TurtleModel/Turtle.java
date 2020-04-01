package slogo.Model.TurtleModel;

/**
 * The backend representation of a Turtle
 */
public class Turtle implements ImmutableTurtle{
    private static final double DEFAULT_PEN_THICKNESS = 10;
    private double myX;
    private double myY;
    private double myHeading;
    private double myPenState;
    private double myShowing;
    private double myIsActive;
    private double myPenThickness;
    private double myPenColorIndex;
    private double myTurtleImageIndex;
    private double myId;

    /**
     * Instantiates a default turtle at 0, 0
     * @param id is the id of the new turtle
     */
    public Turtle(double id){
        this(0, 0, 0, 1, 1, 1, DEFAULT_PEN_THICKNESS, 0,0, id);
    }

    /**
     * Creates a turtle based on the attributes of another turtle
     * @param originalTurtle the turtle to copy
     */
    public Turtle(ImmutableTurtle originalTurtle){
        this.myX = originalTurtle.getX();
        this.myY = originalTurtle.getY();
        this. myHeading = originalTurtle.getHeading();
        this.myPenState = originalTurtle.getPenState();
        this.myShowing = originalTurtle.getShowing();
        this.myIsActive = originalTurtle.isActive();
        this.myPenThickness = originalTurtle.getPenThickness();
        this.myPenColorIndex = originalTurtle.getPenColorIndex();
        this.myTurtleImageIndex = originalTurtle.getTurtleImageIndex();
        this.myId = originalTurtle.getID();
    }

    /**
     * Creates a turtle and sets al its attributes
     * @param x
     * @param y
     * @param heading
     * @param penState
     * @param showing
     * @param isActive
     * @param penThickness
     * @param penColorIndex
     * @param turtleImageIndex
     * @param id
     */
    public Turtle(double x, double y, double heading, double penState, double showing, double isActive,
                           double penThickness, double penColorIndex, double turtleImageIndex, double id){
        this.myX = x;
        this.myY = y;
        this.myHeading = heading;
        this.myPenState = penState;
        this.myShowing = showing;
        this.myIsActive = isActive;
        this.myPenThickness = penThickness;
        this.myPenColorIndex = penColorIndex;
        this.myTurtleImageIndex = turtleImageIndex;
        this.myId = id;
    }

    /**
     * @return an ImmutableTurtle with the same status as this turtle
     */
    public ImmutableTurtle getImmutableTurtle(){
        return new Turtle(this);
    }

    /**
     * Sets the Turtle's x coordinate
     * @param x
     */
    public void setX(double x) {
        myX = x;
    }

    /**
     * Sets the Turtle's y coordinate
     * @param y
     */
    public void setY(double y) {
        myY = y;
    }

    /**
     * Sends the Turtle back to 0, 0
     */
    public void setToHome() {
        myX = 0;
        myY = 0;
    }

    /**
     * Sets the heading of the turtle
     * @param heading
     */
    public void setHeading(double heading) {
        myHeading = heading;
    }

    /**
     * Sets the pen status of the turtle
     * @param state where 1.0 represents down and 0.0 represents up
     */
    public void setPenState(int state) {
        myPenState = state;
    }

    /**
     * Sets whether or not the turtle is displayed
     * @param showing where 1 represents showing and 0 represents not
     */
    public void setShowing(int showing) {
        myShowing = showing;
    }

    /**
     * Sets whether or not the turtle is active
     * @param isActive where 1.0 represents active and 0.0 represents inactive
     */
    public void setIsActive(double isActive) {
        myIsActive = isActive;
    }

    /**
     * Sets the pen color index
     * @param myPenColorIndex
     */
    public void setPenColorIndex(double myPenColorIndex) {
        this.myPenColorIndex = myPenColorIndex;
    }

    /**
     * Sets the pen thickness in pixels
     * @param myPenThickness
     */
    public void setPenThickness(double myPenThickness) {
        this.myPenThickness = myPenThickness;
    }

    /**
     * Sets the turtle image index
     * @param myTurtleImageIndex
     */
    public void setTurtleImageIndex(double myTurtleImageIndex) {
        this.myTurtleImageIndex = myTurtleImageIndex;
    }

    /**
     * Increments x by the given amount
     * @param addX
     */
    public void incrementX(double addX){
        myX+=addX;
    }

    /**
     * Increments y by the given amount
     * @param addY
     */
    public void incrementY(double addY){
        myY+=addY;
    }

    /**
     * Increments the heading by the given amount
     * @param turn
     */
    public void incrementHeading(double turn){
        myHeading+=turn;
    }

    @Override
    public double getX() {
        return myX;
    }

    @Override
    public double getY() {
        return myY;
    }

    @Override
    public double getHeading() {
        return myHeading;
    }

    @Override
    public double getPenState() {
        return myPenState;
    }

    @Override
    public double getShowing() {
        return myShowing;
    }

    @Override
    public double isActive() {
        return myIsActive;
    }


    @Override
    public double getPenColorIndex() {
        return myPenColorIndex;
    }

    @Override
    public double getPenThickness() {
        return myPenThickness;
    }

    @Override
    public double getTurtleImageIndex() {
        return myTurtleImageIndex;
    }

    @Override
    public double getID() {
        return myId;
    }
}
