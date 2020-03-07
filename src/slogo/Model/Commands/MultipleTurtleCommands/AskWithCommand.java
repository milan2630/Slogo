package slogo.Model.Commands.MultipleTurtleCommands;

import slogo.Model.Commands.BackEndCommand;
import slogo.Model.Commands.ControlStructures.IfCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.Parsing.TurtleIterator;
import slogo.Model.Parsing.TurtleModelManager;
import slogo.Model.TurtleModel.Turtle;

import java.util.ArrayList;
import java.util.List;

public class AskWithCommand extends BackEndCommand {
    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        TurtleModelManager turtleModelManager = commandManager.getTurtleManager();
        List<Double> initialActive = turtleModelManager.getActiveTurtleIDs();
        turtleModelManager.activateAll();
        String parsingString = "if";
        parsingString += removeOuterBrackets(params.get(0));
        parsingString += params.get(1);
        double ret = commandManager.parseCommands(parsingString);
        turtleModelManager.activateTurtles(initialActive);
        return ret;
    }
}
