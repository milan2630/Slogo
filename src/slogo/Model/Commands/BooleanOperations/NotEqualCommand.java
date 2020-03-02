package slogo.Model.Commands.BooleanOperations;

import slogo.Model.Commands.Command;

public class NotEqualCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }
}
