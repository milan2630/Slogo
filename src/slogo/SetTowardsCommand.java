package slogo;

public class SetTowardsCommand implements TurtleCommand {

    private int towardsX;
    private int towardsY;

    public SetTowardsCommand(){
    }

    public SetTowardsCommand(int x, int y) {
        towardsX = x;
        towardsY = y;
    }

    public int getX() {
        return towardsX;
    }

    public int getY() {
        return towardsY;
    }

    @Override
    public int getTurtleNum() {
        return 0;
    }

    @Override
    public int getNumArguments() {
        return 2;
    }

}