package slogo.Model.Parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LanguageConverter {
    private Map<Pattern, String> oldCommands;
    private Map<Pattern, String> newCommands;
    private ResourceBundle myOldLanguageResources;
    private ResourceBundle myNewLanguageResources;

    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private static final String COMMAND_NAME_RESOURCES = "languages";
    private static final String DEFAULT_COMMAND_NAME_PACKAGE = COMMAND_NAME_RESOURCES + ".";
    private static final String DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_COMMAND_NAME_PACKAGE;

    public LanguageConverter(String oldLanguage) {
        updateLanguage(oldLanguage);
    }

    public String translateString(String command, String newLanguage) {
        initializeSecondLanguage(newLanguage);
        String[] splits = command.split("\\s");
        splits = command.trim().split("\\s+");
        for (int i = 0; i < splits.length; i++) {
            for (Pattern key: oldCommands.keySet())
            if (match(splits[i], key)) {
                String commandString = oldCommands.get(key);
                System.out.println(commandString);
                for (Pattern newString: newCommands.keySet()){
                    if (match(commandString, newString))
                        splits[i] = newCommands.get(newString);
                }

            }

        }
        String result = String.join(" ", splits);
        return result;
    }

    public String getCommand(String command) {
        String result = "";
        command = command.trim();
        for (Pattern key : oldCommands.keySet())
            if (match(command, key))
                result = oldCommands.get(key);
        return result;
    }

    public void updateLanguage(String language) {
        myOldLanguageResources = ResourceBundle.getBundle(DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE + language);
        initializeMap();
    }

    private void initializeMap() {
        oldCommands = new HashMap<>();
        for (String key : myOldLanguageResources.keySet()) {
            String val = myOldLanguageResources.getString(key);
            String[] options = val.split("\\|");
            for (String option : options) {
                oldCommands.put(Pattern.compile(option), key);
            }
        }
    }

    private void initializeSecondLanguage(String newLanguage) {
        myNewLanguageResources = ResourceBundle.getBundle(DEFAULT_COMMAND_NAME_RESOURCE_PACKAGE + newLanguage);
        newCommands = new HashMap<>();
        for (String key : myNewLanguageResources.keySet()) {
            String val = myNewLanguageResources.getString(key);
            String[] options = val.split("\\|");
            newCommands.put(Pattern.compile(key), options[0]);
        }
    }

    private boolean match(String text, Pattern regex) {
       return  regex.matcher(text).matches();
    }
}
