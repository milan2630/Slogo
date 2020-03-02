package slogo.Model.Commands.BooleanOperations;

import slogo.Model.Commands.Command;

public class OrCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }
}
