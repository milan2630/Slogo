package slogo;

import slogo.Commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandFactory {

    private static final String CLASS_PREFIX = "slogo.Commands.";
    private static final String CLASS_SUFFIX = "Command";
    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";

    private static final String COMMAND_NAME_RESOURCES = "languages";
    private static final String DEFAULT_COMMAND_NAME_PACKAGE = COMMAND_NAME_RESOURCES + ".";
    private static final String DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_COMMAND_NAME_PACKAGE;

    private static final String COMMAND_LOCATION_RESOURCES = "CommandPackageLocations";
    private static final String DEFAULT_COMMAND_LOCATION_PACKAGE = COMMAND_LOCATION_RESOURCES + ".";
    private static final String DEFAULT_COMMAND_LOCATION_FILENAME = "CommandLocations";
    private static final String DEFAULT_COMMAND_LOCATION_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_COMMAND_LOCATION_PACKAGE + DEFAULT_COMMAND_LOCATION_FILENAME;

    private ResourceBundle myCommandNameResources;
    private ResourceBundle myCommandLocationResources;

    private Map<String, String> commands;
    private MethodExplorer methodExplorer;

    public CommandFactory(String lang, MethodExplorer me){
        myCommandLocationResources = ResourceBundle.getBundle(DEFAULT_COMMAND_LOCATION_RESOURCE_PACKAGE);
        setupLanguage(lang);
        methodExplorer = me;
    }

    public void setupLanguage(String lang) {
        myCommandNameResources = ResourceBundle.getBundle(DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE + lang);
        initializeMap();
    }

    public Command getCommand(String commandCall) throws ParsingException{
        if(isUserDefined(commandCall)){
            return methodExplorer.getMethod(commandCall);
        }
        try {
            String commandName = commands.get(commandCall);
            String className = CLASS_PREFIX + myCommandLocationResources.getString(commandName) + "." + commandName + CLASS_SUFFIX;
            System.out.println(className);
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

    public boolean isCommand(String item) {
        return methodExplorer.getMethod(item) != null || commands.containsKey(item);
    }

    public boolean isUserDefined(String commandName){
        return methodExplorer.getMethod(commandName) != null;
    }

    private void initializeMap() {
        commands = new HashMap<>();
        for (String key : myCommandNameResources.keySet()) {
            String val = myCommandNameResources.getString(key);
            String[] options = val.split("\\|");
            for(String option: options){
                commands.put(option, key);
            }
        }
    }


}
