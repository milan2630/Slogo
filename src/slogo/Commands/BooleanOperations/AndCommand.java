package slogo.Commands.BooleanOperations;

import slogo.CommandManager;
import slogo.Commands.BackEndCommand;
import slogo.Commands.Command;
import slogo.ParsingException;
import slogo.Turtle;

import java.util.List;

public class AndCommand extends BackEndCommand {
    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        if(getDoubleParameter(params.get(0), commandManager.getVariableExplorer()) != 0 && getDoubleParameter(params.get(1), commandManager.getVariableExplorer()) != 0){
            return 1.0;
        }
        return 0.0;
    }
}
