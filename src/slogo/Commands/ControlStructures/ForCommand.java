package slogo.Commands.ControlStructures;

import slogo.CommandManager;
import slogo.Commands.BackEndCommand;
import slogo.ParsingException;
import slogo.Turtle;

import java.util.List;

public class ForCommand extends BackEndCommand {

    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        String[] argParts = params.get(0).split(" ");
        String name = argParts[1];
        double start = getDoubleParameter(argParts[2], commandManager.getVariableExplorer());
        double end = getDoubleParameter(argParts[3], commandManager.getVariableExplorer());
        double iterator = getDoubleParameter(argParts[4], commandManager.getVariableExplorer());
        double ret = repeatAction(commandManager, params.get(1), name, start, end, iterator);
        commandManager.getVariableExplorer().removeVariableByName(name);
        return ret;
    }
}
