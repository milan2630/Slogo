package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.BackEndCommand;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.TurtleModel.Turtle;

import java.util.List;

public class ClearScreenCommand extends BackEndCommand {

    @Override
    public int getNumArguments() {
        return 0;
    }

    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) {
        double oldX = myTurtle.getX();
        double oldY = myTurtle.getY();
        myTurtle.setToHome();
        myTurtle.setHeading(0);
        commandManager.getInternalStates().add(myTurtle.getImmutableTurtle());
        //make sure frontend knows to clear trailview
        return Math.hypot(myTurtle.getX() - oldX, myTurtle.getY() - oldY);
    }
}
