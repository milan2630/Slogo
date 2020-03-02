package slogo.Model.Commands.TurtleQueries;

import slogo.Model.CommandManager;
import slogo.Model.Commands.BackEndCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.TurtleModel.Turtle;

import java.util.List;

public class XCoordinateCommand extends BackEndCommand {
    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        return myTurtle.getX();
    }

    @Override
    public int getNumArguments() {
        return 0;
    }
}
