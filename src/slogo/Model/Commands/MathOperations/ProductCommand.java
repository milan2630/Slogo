package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

public class ProductCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }
}
