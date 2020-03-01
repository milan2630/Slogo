package slogo.Commands;

import slogo.*;
import slogo.Commands.Command;

import java.util.List;

public class MakeVariableCommand extends BackEndCommand {
    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        Variable<Double> var = new DoubleVariable(params.get(0), getDoubleParameter(params.get(1), commandManager.getVariableExplorer()));
        commandManager.getVariableExplorer().addVariable(var);
        return var.getValue();
    }
}
