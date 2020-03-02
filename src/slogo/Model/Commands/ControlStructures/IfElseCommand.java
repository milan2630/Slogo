package slogo.Model.Commands.ControlStructures;

import slogo.Model.Parsing.CommandManager;
import slogo.Model.Commands.BackEndCommand;
import slogo.Model.Parsing.Parser;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.TurtleModel.Turtle;

import java.util.List;

public class IfElseCommand extends BackEndCommand {
    @Override
    public int getNumArguments() {
        return 3;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        double expr = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        int whichToExecute = 1;
        if(expr == 0.0){
            whichToExecute = 2;
        }
        Parser newParser = new Parser(commandManager);
        newParser.parseCommands(params.get(whichToExecute));
        return newParser.getFinalReturn();
    }
}
