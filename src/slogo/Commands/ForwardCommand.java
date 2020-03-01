package slogo.Commands;

import slogo.*;

import java.util.List;

public class ForwardCommand implements Command {

    @Override
    public int getNumArguments() {
        return 1;
    }

    public double executeCommand(CommandManager commandManager, List<String> params) throws ParsingException {
        Double pixForward = getDoubleParameter(params.get(0), commandManager.getVariableExplorer());
        Turtle myTurtle = commandManager.getTurtle();
        myTurtle.incrementX(pixForward * Math.sin(Math.toRadians(myTurtle.getHeading())));
        myTurtle.incrementY(pixForward * Math.cos(Math.toRadians(myTurtle.getHeading())));
        commandManager.getInternalStates().add(myTurtle.getImmutableTurtle());
        return pixForward;
    }

    private double getDoubleParameter(String val, VariableExplorer variableExplorer) throws ParsingException {
        try{
            return Double.parseDouble(val);
        }
        catch (NumberFormatException e){
            if(variableExplorer.getVariable(val) != null){
                return (Double) variableExplorer.getVariable(val).getValue();
            }
            else if(val.indexOf(":") == 0){
                variableExplorer.addDoubleVarByName(val, 0.0);
                return 0.0;
            }
            else{
                throw new ParsingException("UnrecognizedEntity", val);
            }
        }
    }

}
