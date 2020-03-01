package slogo.Commands;

import slogo.CommandManager;
import slogo.ParsingException;
import slogo.Turtle;

import java.util.List;

public class BackwardCommand extends BackEndCommand {

    @Override
    public int getNumArguments() {
        return 1;
    }

    @Override
    public double executeCommand(CommandManager commandManager, List<String> params) throws ParsingException {
        Double pixBackward = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        for(Turtle myTurtle: commandManager.getTurtles()){
            if(myTurtle.isActive()){
                myTurtle.incrementX(pixBackward * Math.sin(Math.toRadians(myTurtle.getHeading())) * -1);
                myTurtle.incrementY(pixBackward * Math.cos(Math.toRadians(myTurtle.getHeading())) * -1);
                commandManager.getInternalStates().add(myTurtle.getImmutableTurtle());

            }
        }
        return pixBackward;

    }
}
