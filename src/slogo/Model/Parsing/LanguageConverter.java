package slogo.Model.Parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LanguageConverter {
    private Map<String, String> oldCommands;
    private Map<String, String> newCommands;
    private ResourceBundle myOldLanguageResources;
    private ResourceBundle myNewLanguageResources;

    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private static final String COMMAND_NAME_RESOURCES = "languages";
    private static final String DEFAULT_COMMAND_NAME_PACKAGE = COMMAND_NAME_RESOURCES + ".";
    private static final String DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_COMMAND_NAME_PACKAGE;

    public LanguageConverter(String oldLanguage, String newLanguage){
        myOldLanguageResources = ResourceBundle.getBundle(DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE + oldLanguage);
        myNewLanguageResources = ResourceBundle.getBundle(DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE + newLanguage);
        initializeMap();
    }

    public String translateString(String command) {
        String[] splits = command.split("\\s");
        splits = command.trim().split("\\s+");
        for (int i = 0; i < splits.length; i++) {
            if (oldCommands.containsKey(splits[i])) {
                String commandString = oldCommands.get(splits[i]);
                splits[i] = newCommands.get(commandString);
            }
        }
        String result = String.join(" ", splits);
        return result;
    }

    private void initializeMap() {
        oldCommands = new HashMap<>();
        for (String key : myOldLanguageResources.keySet()) {
            String val = myOldLanguageResources.getString(key);
            String[] options = val.split("\\|");
            for(String option: options){
                oldCommands.put(option, key);
            }
        }
        newCommands = new HashMap<>();
        for (String key : myNewLanguageResources.keySet()) {
            String val = myNewLanguageResources.getString(key);
            String[] options = val.split("\\|");
            newCommands.put(key, options[0]);
        }
    }
}
