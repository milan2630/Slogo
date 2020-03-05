package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.BackEndCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.TurtleModel.Turtle;

import java.util.List;

public class SetHeadingCommand extends BackEndCommand {

    @Override
    public int getNumArguments() {
        return 1;
    }

    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        Double heading = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        myTurtle.setHeading(heading);
        commandManager.getInternalStates().add(myTurtle.getImmutableTurtle());
        return heading;
    }

}
