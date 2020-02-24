package slogo;

public class Turtle {

    private int myX;
    private int myY;
    private int myHeading;
    private int penState;

    public Turtle(){
        myX = 0;
        myY = 0;
        myHeading = 0;
        penState = 1;
    }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public int getHeading() {
        return myHeading;
    }

    public int getPenState(){
        return penState;
    }
}
