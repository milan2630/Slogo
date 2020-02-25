package slogo;

import java.util.List;

public class ShowTurtleCommand implements TurtleCommand {

    public ShowTurtleCommand() {
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
