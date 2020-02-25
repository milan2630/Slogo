package slogo;

import java.util.List;

public class SetHeadingCommand implements TurtleCommand {

    private int heading;

    public SetHeadingCommand() {
    }

    public SetHeadingCommand(int heading) {
        this.heading = heading;
    }

    public int getHeading() {
        return heading;
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
        heading = arguments.get(0);
    }
}
