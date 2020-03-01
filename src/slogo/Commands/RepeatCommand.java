package slogo.Commands;

import slogo.CommandManager;
import slogo.ParsingException;
import slogo.Turtle;

import java.util.List;

public class RepeatCommand extends BackEndCommand{

    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        double numTimes = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        double ret = repeatAction(commandManager, params.get(1), ":repcount", 1.0, numTimes, 1.0);
        commandManager.getVariableExplorer().removeVariableByName(":repcount");
        return ret;
    }
}
