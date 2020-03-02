package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;

public class SetPositionCommand implements Command {


    @Override
    public int getNumArguments() {
        return 2;
    }

}
