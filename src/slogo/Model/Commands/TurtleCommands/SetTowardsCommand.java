package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;

public class SetTowardsCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }

}