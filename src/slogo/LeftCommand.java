package slogo;

public class LeftCommand implements TurtleCommand {

    private int degreesLeft;

    public LeftCommand() {
    }

    public LeftCommand(int degrees) {
        degreesLeft = degrees;
    }

    public int getDegreesLeft() {
        return degreesLeft;
    }

    @Override
    public int getTurtleNum() {
        return 0;
    }

    @Override
    public int getNumArguments() {
        return 1;
    }

}