package slogo.Model.TurtleModel;

public class ImmutableTurtle {

    private double myX;
    private double myY;
    private double myHeading;
    private int penState;
    private int showing;

    public ImmutableTurtle(double x, double y, double heading, int pen, int show){
        myX = x;
        myY = y;
        myHeading = heading;
        penState = pen;
        showing = show;
    }

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

}
