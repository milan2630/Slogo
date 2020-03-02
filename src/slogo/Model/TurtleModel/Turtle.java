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



    private double setHeading(SetHeadingCommand setHeading, List<String> params) throws ParsingException {
        Double heading = getDoubleParameter(params.get(0));
        myHeading = heading;
        return myHeading;
    }

    private double setTowards(SetTowardsCommand setTowards, List<String> params) throws ParsingException {
        Double towardX = getDoubleParameter(params.get(0));
        Double towardY = getDoubleParameter(params.get(1));

        if(towardX == myX && towardY == myY) {
            throw new ParsingException("TowardSelfException");
        }

        double oldHeading = myHeading;
        myHeading = Math.toDegrees(Math.atan((towardX-myX)/(towardY-myY)));
        return myHeading - oldHeading;
    }

    private double setPosition(SetPositionCommand setPosition, List<String> params) throws ParsingException {
        double oldX = myX;
        double oldY = myY;
        myX = getDoubleParameter(params.get(0));
        myY = getDoubleParameter(params.get(1));
        return Math.hypot(myX - oldX, myY - oldY);
    }

    private double setPenDown(PenDownCommand penDown, List<String> params) throws ParsingException {
        penState = 1;
        return penState;
    }

    private double setPenUp(PenUpCommand penUp, List<String> params) throws ParsingException {
        penState = 0;
        return penState;
    }

    private double showTurtle(ShowTurtleCommand showTurtle, List<String> params) throws ParsingException {
        showing = 1;
        return showing;
    }

    private double hideTurtle(HideTurtleCommand hideTurtle, List<String> params) throws ParsingException {
        showing = 0;
        return showing;
    }

    private double goHome(GoHomeCommand goHome, List<String> params) throws ParsingException {
        double oldX = myX;
        double oldY = myY;
        setToHome();
        return Math.hypot(myX - oldX, myY - oldY);
    }

    private double subtract(DifferenceCommand command, List<String> params) throws ParsingException {
        return getDoubleParameter(params.get(0)) - getDoubleParameter(params.get(1));
    }

    private double multiply(ProductCommand command, List<String> params) throws ParsingException {
        return getDoubleParameter(params.get(0)) * getDoubleParameter(params.get(1));
    }

    private double divide(QuotientCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(1)) == 0){
            throw new ParsingException("DivideZero", params.get(1));
        }
        return getDoubleParameter(params.get(0)) / getDoubleParameter(params.get(1));
    }

    private double getRemainder(RemainderCommand command, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(1)) == 0){
            throw new ParsingException("DivideZero", params.get(1));
        }
        return getDoubleParameter(params.get(0)) % getDoubleParameter(params.get(1));
    }

    private double negate(MinusCommand command, List<String> params) throws ParsingException {
        return getDoubleParameter(params.get(0)) *-1;
    }

    private double getRandomNum(RandomCommand command, List<String> params) throws ParsingException{
        return getDoubleParameter(params.get(0)) * Math.random();
    }

    private double getSine(SineCommand command, List<String> params) throws ParsingException{
        return Math.sin(getDoubleParameter(params.get(0)));
    }

    private double getCos(CosineCommand command, List<String> params) throws ParsingException{
        return Math.cos(getDoubleParameter(params.get(0)));
    }

    private double getTan(TangentCommand command, List<String> params) throws ParsingException{
        return Math.tan(getDoubleParameter(params.get(0)));
    }

    private double getArcTan(ArcTangentCommand command, List<String> params) throws ParsingException{
        return Math.atan(getDoubleParameter(params.get(0)));
    }

    private double getNaturalLog(NaturalLogCommand command, List<String> params) throws ParsingException{
        if(getDoubleParameter(params.get(0)) < 0){
            throw new ParsingException("LogOfNeg");
        }
        return Math.log(getDoubleParameter(params.get(0)));
    }

    private double raiseToPower(PowerCommand command, List<String> params) throws ParsingException {
        return Math.pow(getDoubleParameter(params.get(0)), getDoubleParameter(params.get(1)));
    }

    private double getPi(PiCommand command, List<String> params) throws ParsingException {
        return Math.PI;
    }

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
