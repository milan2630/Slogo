package slogo;

public class ImmutableTurtle {

    private int myX;
    private int myY;
    private int myHeading;
    private int penState;
    private int showing;

    public ImmutableTurtle(int x, int y, int heading, int pen, int show){
        myX = x;
        myY = y;
        myHeading = heading;
        penState = pen;
        showing = show;
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

    public int getShowing(){
        return showing;
    }

}
