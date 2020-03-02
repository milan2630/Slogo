package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

public class PowerCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }
}
