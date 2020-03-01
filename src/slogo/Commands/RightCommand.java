package slogo.Commands;

import slogo.CommandManager;
import slogo.ParsingException;
import slogo.Turtle;

import java.util.List;

public class RightCommand extends BackEndCommand {

    @Override
    public int getNumArguments() {
        return 1;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        Double degreesRight = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        myTurtle.incrementHeading(degreesRight);
        commandManager.getInternalStates().add(myTurtle.getImmutableTurtle());
        return degreesRight;
    }
}
