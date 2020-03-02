package slogo.Model.Commands;

import slogo.Model.Parsing.CommandManager;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.Variables.Variable;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.Model.Parsing.Parser;
import slogo.Model.TurtleModel.Turtle;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public double repeatAction(CommandManager commandManager, String command, String iteratorName, double startVal, double endVal, double iterationVal) throws ParsingException {
        Variable<Double> var = commandManager.getVariableExplorer().addDoubleVarByName(iteratorName, startVal);
        Parser newParser = new Parser(commandManager);
        while(var.getValue() <= endVal){
            newParser.parseCommands(command);
            var.setValue(var.getValue()+iterationVal);
        }
        return newParser.getFinalReturn();
    }

    public String removeOuterBrackets(String input){
        if(input.charAt(0) == '[' && input.charAt(input.length()-1) == ']'){
            return input.substring(1, input.length()-1);
        }
        return input;
    }

    public boolean isZero(double input){
        return new BigDecimal(input).setScale(13, RoundingMode.HALF_UP).doubleValue() == 0.0;
    }

    public String toString(){
        String[] nameParts = this.getClass().toString().split("\\.");
        String className = nameParts[nameParts.length - 1];
        return className.split("@")[0];
    }

}
