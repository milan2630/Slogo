package slogo.Model.Commands;

import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.Variables.Variable;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.Model.Parsing.CommandManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ResourceBundle;

/**
 * Abstract class that contains several helper methods for the command classes
 */
public abstract class BackEndCommand implements Command {
    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private static final String COMMAND_ARGUMENT_RESOURCES = "CommandArgumentCount";
    private static final String DEFAULT_COMMAND_ARGUMENT_PACKAGE = COMMAND_ARGUMENT_RESOURCES + ".";
    private static final String DEFAULT_COMMAND_ARGUMENT_FILENAME = "ArgumentsPerCommand";
    private static final String DEFAULT_COMMAND_ARGUMENT_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_COMMAND_ARGUMENT_PACKAGE + DEFAULT_COMMAND_ARGUMENT_FILENAME;
    private static final ResourceBundle myArgCountResources = ResourceBundle.getBundle(DEFAULT_COMMAND_ARGUMENT_RESOURCE_PACKAGE);
    private static final int ROUNDING_PLACES_TO_CHECK_ZERO = 13;
    private static final char START_BRACKET = '[';
    private static final char END_BRACKET = ']';
    private static final String CLASS_SUFFIX = "Command";
    public static final String VARIABLE_INDICATOR = ":";

    @Override
    public int getNumArguments(){
        String key = this.toString().replace(CLASS_SUFFIX, "");
        return Integer.parseInt(myArgCountResources.getString(key));
    }


    /**
     * Returns a double based on a parameter whether it is a variable or an actual double
     * @param val the value to parse
     * @param variableExplorer to check if the string is a variable
     * @return the double represented by val
     * @throws ParsingException if val is not a valid double or a variable
     */
    public double getDoubleParameter(String val, VariableExplorer variableExplorer) throws ParsingException {
        try{
            return Double.parseDouble(val);
        }
        catch (NumberFormatException e){
            if(variableExplorer.getVariable(val) != null){
                return (Double) variableExplorer.getVariable(val).getValue();
            }
            else if(val.indexOf(VARIABLE_INDICATOR) == 0){
                variableExplorer.addDoubleVarByName(val, 0.0);
                return 0.0;
            }
            else{
                throw new ParsingException("UnrecognizedEntity", val);
            }
        }
    }

    /**
     * Repeatedly parses a given string
     * @param commandManager used to parse the string
     * @param command is the string to parse
     * @param iteratorName is the name of the variable to hold the looping value
     * @param startVal the start value of the iterator
     * @param endVal the end value of the iterator
     * @param iterationVal how much to iterate by after each loop
     * @return the slogo return value of the last command executed
     * @throws ParsingException
     */
    protected double repeatAction(CommandManager commandManager, String command, String iteratorName, double startVal, double endVal, double iterationVal) throws ParsingException {
        Variable<Double> var = commandManager.getVariableExplorer().addDoubleVarByName(iteratorName, startVal);
        double ret = 0;
        while(var.getValue() <= endVal){
            ret = commandManager.parseCommands(command);
            var.setValue(var.getValue()+iterationVal);
        }
        return ret;
    }

    /**
     * Removes the brackets around a string if they exist
     * @param input the string to be stripped of the brackets
     * @return the string without surrounding brackets
     */
    protected String removeOuterBrackets(String input){
        if(input.charAt(0) == START_BRACKET && input.charAt(input.length()-1) == END_BRACKET){
            return input.substring(1, input.length()-1);
        }
        return input;
    }

    /**
     * Checks if a double is zero by rounding it
     * @param input the double to check if it is zero
     * @return true if the double is zero, false if not
     */
    protected boolean isZero(double input){
        return new BigDecimal(input).setScale(ROUNDING_PLACES_TO_CHECK_ZERO, RoundingMode.HALF_UP).doubleValue() == 0.0;
    }

    /**
     * @return the class's name
     */
    public String toString(){
        String[] nameParts = this.getClass().toString().split("\\.");
        String className = nameParts[nameParts.length - 1];
        return className.split("@")[0];
    }

    /**
     * Parses an integer from a parameter value
     * @param val the parameter to parse into an integer
     * @param variableExplorer to check if val is a variable
     * @return the integer specified by val
     * @throws ParsingException if val is not a valid integer or variable
     */
    protected int getIntegerParameter(String val, VariableExplorer variableExplorer) throws ParsingException {
        double unRounded = getDoubleParameter(val, variableExplorer);
        return  (int) Math.round(unRounded);
    }

}
