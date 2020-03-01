package slogo.Commands;

import slogo.CommandManager;
import slogo.ParsingException;
import slogo.Turtle;
import slogo.VariableExplorer;

import java.util.List;

public abstract class BackEndCommand implements Command {

    public abstract double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException;

    public double getDoubleParameter(String val, VariableExplorer variableExplorer) throws ParsingException {
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
