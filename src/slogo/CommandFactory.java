package slogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandFactory {


    private static final String classSuffix = "Command";
    private static final String RESOURCES = "resources";
    private static final String COMMAND_RESOURCES = "languages";
    public static final String DEFAULT_COMMAND_RESOURCE_PACKAGE = RESOURCES + "/" + COMMAND_RESOURCES + ".";
    private ResourceBundle myResources;

    private Map<String, String> commands;

    public CommandFactory(String lang){
        myResources = ResourceBundle.getBundle(DEFAULT_COMMAND_RESOURCE_PACKAGE + lang);
        initializeMap();
    }

    public Command getCommand(String commandCall) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = commands.get(commandCall) + classSuffix;
        Class commandClass = Class.forName(className);
        Constructor commandConstructor = commandClass.getConstructor();
        return (Command) commandConstructor.newInstance();
    }

    private void initializeMap() {
        commands = new HashMap<>();
        for (String key : myResources.keySet()) {
            String val = myResources.getString(key);
            String[] options = val.split("\\|");
            for(String option: options){
                commands.put(option, key);
            }
        }
    }


}
