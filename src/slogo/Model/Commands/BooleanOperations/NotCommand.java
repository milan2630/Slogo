package slogo.Model.Commands.BooleanOperations;

import slogo.Model.Commands.Command;

public class NotCommand implements Command {
    @Override
    public int getNumArguments() {
        return 1;
    }
}
