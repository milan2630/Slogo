package slogo.Commands.MathOperations;

import slogo.CommandManager;
import slogo.Commands.BackEndCommand;
import slogo.Commands.Command;
import slogo.ParsingException;
import slogo.Turtle;

import java.util.List;

public class SumCommand extends BackEndCommand {
    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        return getDoubleParameter(params.get(0),commandManager.getVariableExplorer()) + getDoubleParameter(params.get(1), commandManager.getVariableExplorer());
    }
}
