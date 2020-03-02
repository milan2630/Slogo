package slogo.Model.Commands.ControlStructures;

import slogo.Model.Commands.BackEndCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.Variables.DoubleVariable;
import slogo.Model.Explorers.Variables.Variable;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.TurtleModel.Turtle;

import java.util.ArrayList;
import java.util.List;

public class UserDefinedInstructionCommand extends BackEndCommand {
    private String myCommands;
    private String name;
    private int numArguments;
    private List<DoubleVariable> arguments;


    public UserDefinedInstructionCommand(String name, String commands, List<String> parNames){
        myCommands=commands;
        this.name = name;
        numArguments = parNames.size();
        arguments = new ArrayList<>();
        for(int i = 0; i < numArguments; i++){
            arguments.add(new DoubleVariable(parNames.get(i), 0.0)); //TODO error checking
        }
    }

    public UserDefinedInstructionCommand(String name, List<String> parNames){
        this(name, new String(), parNames);
    }

    /**
     * adds Command object to List
     * @param command
     */
    public void addCommand(String command){
        myCommands = myCommands + " " + command;
    }

    /**
     * returns unmodifiable myCommands
     * @return unmodifiable List of myCommands
     */
    public String getCommands(){
        return myCommands;
    }

    private String getExecutableCommands(){
        String executable = "";
        for(int i = 0; i < arguments.size(); i++){
            executable = executable + "make " + arguments.get(i).getName() + " " + arguments.get(i).getValue() + " ";
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
     * returns name of this Method object
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
}
