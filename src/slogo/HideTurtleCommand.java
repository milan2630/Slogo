package slogo;

import java.util.List;

public class HideTurtleCommand implements TurtleCommand {

    public HideTurtleCommand() {
    }

    @Override
    public int getTurtleNum() {
        return 0;
    }

    @Override
    public int getNumArguments() {
        return 0;
    }

    @Override
    public void setArguments(List<Integer> arguments) {
    }
}
