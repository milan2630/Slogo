package slogo.Model.TurtleModel;

import java.util.ArrayList;
import java.util.List;

public class Turtle {
    private double myX;
    private double myY;
    private double myHeading;
    private int penState;
    private int showing;
    private int myID;
    private boolean isActive;
    private List<ImmutableTurtle> internalStates;

    public Turtle(int id, boolean active){
        myID = id;
        isActive = active;
        myX = 0;
        myY = 0;
        myHeading = 0;
        penState = 1;
        showing = 1;
        internalStates = new ArrayList<>();
    }

/*
    private double isLesser(LessThanCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0)) < getDoubleParameter(params.get(1))){
            return 1.0;
        }
        return 0.0;
    }

    private double isGreater(GreaterThanCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0)) > getDoubleParameter(params.get(1))){
            return 1.0;
        }
        return 0.0;
    }

    private double isEqual(EqualCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0)) == getDoubleParameter(params.get(1))){
            return 1.0;
        }
        return 0.0;
    }

    private double isNotEqual(NotEqualCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0)) != getDoubleParameter(params.get(1))){
            return 1.0;
        }
        return 0.0;
    }


    private double isOr(OrCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0)) != 0 || getDoubleParameter(params.get(1)) != 0){
            return 1.0;
        }
        return 0.0;
    }

    private double getNot(NotCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0)) == 0){
            return 1.0;
        }
        return 0.0;
    }

*//*
    private double clearScreen(ClearScreenCommand clearScreen, List<String> params) throws ParsingException {
        double oldX = myX;
        double oldY = myY;
        setToHome();
        // TODO: tell Controller and clear TrailView in Visualizer, won't work until design change
        return Math.hypot(myX - oldX, myY - oldY);
    }
 *//*
*/
    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    public double getHeading() {
        return myHeading;
    }

    public int getPenState(){
        return penState;
    }

    public int getShowing(){
        return showing;
    }

    public ImmutableTurtle getImmutableTurtle(){
        return new ImmutableTurtle(myX, myY, myHeading, penState, showing);
    }

    public void setX(double x) {
        myX = x;
    }

    public void setY(double y) {
        myY = y;
    }

    public void setToHome() {
        myX = 0;
        myY = 0;
    }

    public void setHeading(double heading) {
        myHeading = heading;
    }

    public void setPenState(int state) {
        penState = state;
    }

    public void setShowing(int showing) {
        this.showing = showing;
    }

    public List<ImmutableTurtle> getInternalStates() {
        List<ImmutableTurtle> copy = new ArrayList<>(internalStates);
        internalStates = new ArrayList<>();
        return copy;
    }

    public void incrementX(double addX){
        myX+=addX;
    }

    public void incrementY(double addY){
        myY+=addY;
    }

    public boolean isActive() {
        return isActive;
    }

    public void incrementHeading(double turn){
        myHeading+=turn;
    }
}
