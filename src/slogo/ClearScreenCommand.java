package slogo;

import java.util.List;

public class ClearScreenCommand implements TurtleCommand {

    public ClearScreenCommand() {
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
