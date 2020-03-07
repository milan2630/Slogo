package slogo.Model.Explorers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Parsing.LanguageHandler;

import java.util.HashMap;

public class MethodExplorer {

    private ObservableMap<String, UserDefinedInstructionCommand> methodMap;
    private LanguageHandler languageHandler;
    public MethodExplorer(LanguageHandler language){
        methodMap = FXCollections.observableMap(new HashMap<>());
        languageHandler = language;
    }

    public void addMethod(UserDefinedInstructionCommand method) throws ParsingException {
        methodMap.put(method.getName(), method);
    }


    public UserDefinedInstructionCommand getMethod(String name){
        if(methodMap.keySet().contains(name)){
            return methodMap.get(name);
        }
        return null;
    }

    public void convertLanguage( String newLanguage){
        for (String name: methodMap.keySet()){
            methodMap.get(name).translateCommands(languageHandler, newLanguage);
        }
    }

    public ObservableMap<String, UserDefinedInstructionCommand> getMethodNames(){
        return methodMap;
    }

}
