package slogo.Commands;

import slogo.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class IfCommand extends BackEndCommand{

    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        double expr = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        if(isZero(expr)){
            return 0.0;
        }
        Parser newParser = new Parser(commandManager);
        newParser.parseCommands(params.get(1));
        return newParser.getFinalReturn();
    }
}
