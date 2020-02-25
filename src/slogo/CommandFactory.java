package slogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandFactory {

    private static final String CLASS_PREFIX = "slogo.";
    private static final String CLASS_SUFFIX = "Command";
    private static final String RESOURCES = "resources";
    private static final String COMMAND_RESOURCES = "languages";
    public static final String DEFAULT_COMMAND_RESOURCE_PACKAGE = RESOURCES + "/" + COMMAND_RESOURCES + ".";
    private ResourceBundle myResources;

    private Map<String, String> commands;
    private MethodExplorer methodExplorer;

    public CommandFactory(String lang, MethodExplorer me){
        myResources = ResourceBundle.getBundle(DEFAULT_COMMAND_RESOURCE_PACKAGE + lang);
        initializeMap();
        methodExplorer = me;
    }

    public Command getCommand(String commandCall) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SlogoMethod userMethod = methodExplorer.getMethod(commandCall);
        if(userMethod != null){
            return userMethod;
        }
        String className = CLASS_PREFIX + commands.get(commandCall) + CLASS_SUFFIX;
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
