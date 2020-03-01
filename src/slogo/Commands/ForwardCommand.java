package slogo.Commands;

import slogo.*;

import java.util.List;

public class ForwardCommand extends BackEndCommand {

    @Override
    public int getNumArguments() {
        return 1;
    }

    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        Double pixForward = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        myTurtle.incrementX(pixForward * Math.sin(Math.toRadians(myTurtle.getHeading())));
        myTurtle.incrementY(pixForward * Math.cos(Math.toRadians(myTurtle.getHeading())));
        commandManager.getInternalStates().add(myTurtle.getImmutableTurtle());
        return pixForward;
    }
}
