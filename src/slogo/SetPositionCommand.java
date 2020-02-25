package slogo;

import java.util.List;

public class SetPositionCommand implements TurtleCommand {

    private int positionX;
    private int positionY;

    public SetPositionCommand() {
    }

    public SetPositionCommand(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
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
        positionX = arguments.get(0);
        positionY = arguments.get(1);
    }
}
