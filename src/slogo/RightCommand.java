package slogo;

import java.util.List;

public class RightCommand implements TurtleCommand {

    private int degreesRight;

    public RightCommand() {
    }

    public RightCommand(int degrees) {
        degreesRight = degrees;
    }

    public int getDegreesRight() {
        return degreesRight;
    }

    @Override
    public int getTurtleNum() {
        return 0;
    }

    @Override
    public int getNumArguments() {
        return 1;
    }

    @Override
    public void setArguments(List<Integer> arguments) {
        degreesRight = arguments.get(0);
    }
}
