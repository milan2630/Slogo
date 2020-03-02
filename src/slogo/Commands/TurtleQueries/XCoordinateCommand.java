package slogo.Commands.TurtleQueries;

import slogo.CommandManager;
import slogo.Commands.BackEndCommand;
import slogo.ParsingException;
import slogo.Turtle;

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
