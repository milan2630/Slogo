package slogo.Model.Parsing;

import slogo.Model.Commands.Command;
import slogo.Model.Explorers.MethodExplorer;
import slogo.Model.ErrorHandling.ParsingException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Instantiates different commands and determines if strings specify commands
 */
public class CommandFactory {

    private static final String CLASS_PREFIX = "slogo.Model.Commands.";
    private static final String CLASS_SUFFIX = "Command";
    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private static final String COMMAND_LOCATION_RESOURCES = "CommandPackageLocations";
    private static final String DEFAULT_COMMAND_LOCATION_PACKAGE = COMMAND_LOCATION_RESOURCES + ".";
    private static final String DEFAULT_COMMAND_LOCATION_FILENAME = "CommandLocations";
    private static final String DEFAULT_COMMAND_LOCATION_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_COMMAND_LOCATION_PACKAGE + DEFAULT_COMMAND_LOCATION_FILENAME;

    private ResourceBundle myCommandLocationResources;

    private MethodExplorer methodExplorer;
    private LanguageConverter languageConverter;

    /**
     * Creates a CommandFactory with a given MethodExplorer and in the given language
     * @param lang properties file specifying keys for commands
     * @param me Method Explorer with user defined methods
     */
    public CommandFactory(LanguageConverter lang, MethodExplorer me){
        myCommandLocationResources = ResourceBundle.getBundle(DEFAULT_COMMAND_LOCATION_RESOURCE_PACKAGE);
        languageConverter = lang;
        methodExplorer = me;
    }

    /**
     * Used to get an Instantiated Command Object based on a command call
     * @param commandCall the key associated with a Command
     * @return a Command specified by commandCall
     * @throws ParsingException if there is an issue instantiating the command
     */
    public Command getCommand(String commandCall) throws ParsingException {
        if(isUserDefined(commandCall)){
            return methodExplorer.getMethod(commandCall);
        }
        try {
            String commandName = getCommandOfficialName(commandCall);
            String className = CLASS_PREFIX + myCommandLocationResources.getString(commandName) + "." + commandName + CLASS_SUFFIX;
            Class<?> commandClass = Class.forName(className);
            Constructor<?> commandConstructor = commandClass.getConstructor();
            return (Command) commandConstructor.newInstance();
        } catch (ClassNotFoundException e) {
            throw new ParsingException("NoCommand", commandCall);
        } catch (NoSuchMethodException e) {
            throw new ParsingException("NoCommandConstructor", commandCall);
        } catch (IllegalAccessException e) {
            throw new ParsingException("CannotAccessCommand", commandCall);
        } catch (InstantiationException e) {
            throw new ParsingException("CannotInstantiateCommand", commandCall);
        } catch (InvocationTargetException e) {
            throw new ParsingException("CommandConstructorError", commandCall);
        }
    }

    /**
     * @param item is the word being checked to see if it is a command
     * @return whether item is a command or not
     */
    public boolean isCommand(String item) {
        return methodExplorer.getMethod(item) != null || getCommandOfficialName(item) != null;
    }

    private boolean isUserDefined(String commandName){
        return methodExplorer.getMethod(commandName) != null;
    }


    private String getCommandOfficialName(String command){
        return languageConverter.getCommandOfficialName(command);
    }

}
