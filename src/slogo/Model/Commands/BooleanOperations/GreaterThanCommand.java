package slogo.Model.Commands.BooleanOperations;

import slogo.Model.Commands.Command;

public class GreaterThanCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }
}
