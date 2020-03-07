package slogo.Model.Commands.ControlStructures;

import slogo.Model.Commands.BackEndCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.Variables.DoubleVariable;
import slogo.Model.Explorers.Variables.Variable;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Model.TurtleModel.Turtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Class who's instances are user-defined commands
 */
public class UserDefinedInstructionCommand extends BackEndCommand {
    private static final String MAKE_DEFAULT = "make";
    private String myCommands;
    private String name;
    private int numArguments;
    private List<DoubleVariable> arguments;
    private String make;

    /**
     * Instantiates a user-defined command with a name, parameter names, and a string of commands to run
     * @param name of the command
     * @param commands the string to be run whenever the command is called
     * @param parNames the list of parameter names
     */
    public UserDefinedInstructionCommand(String name, String commands, List<String> parNames){
        myCommands=commands;
        this.name = name;
        numArguments = parNames.size();
        arguments = new ArrayList<>();
        for(int i = 0; i < numArguments; i++){
            arguments.add(new DoubleVariable(parNames.get(i), 0.0)); //TODO error checking
        }
        make = MAKE_DEFAULT;
    }

    /**
     * @return the commands to run when this command is called
     */
    public String getCommands(){
        return myCommands;
    }

    private String getExecutableCommands(){
        String executable = "";
        for(int i = 0; i < arguments.size(); i++){
            executable = executable + make+" "+ arguments.get(i).getName() + " " + arguments.get(i).getValue() + " ";
        }
        executable = executable + myCommands;
        return executable;
    }

    private List<String> getArgumentNames(){
        List<String> names = new ArrayList<>();
        for(Variable var: arguments){
            names.add(var.getName());
        }
        return names;
    }


    /**
     * returns name of this user-defined command
     * @return name
     */
    public String getName(){
        return name;
    }

    @Override
    public int getNumArguments() {
        return numArguments;
    }

    private void setArguments(List<Double> args) {
        for(int i = 0; i < args.size(); i++){
            arguments.get(i).setValue(args.get(i));
        }
    }

    @Override
    public double executeCommand(CommandManager commandManager, Turtle myTurtle, List<String> params) throws ParsingException {
        List<Double> inputs = new ArrayList<>();
        for(int i = 0; i < params.size(); i++){
            inputs.add(getDoubleParameter(params.get(i), commandManager.getVariableExplorer()));
        }
        setArguments(inputs);
        String com = getExecutableCommands();
        double ret = commandManager.parseCommands(com);
        commandManager.getVariableExplorer().removeVariablesByNames(getArgumentNames());
        return ret;
    }

    public void translateCommands(LanguageHandler languageHandler, String newLanguage) {
        String oldCommands = myCommands;
        myCommands = languageHandler.translateString(oldCommands, newLanguage);
        make = languageHandler.translateString(make, newLanguage);
    }
}
