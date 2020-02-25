package slogo;

import java.util.List;

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

    @Override
    public void setArguments(List<Integer> arguments) {
        towardsX = arguments.get(0);
        towardsY = arguments.get(1);
    }
}