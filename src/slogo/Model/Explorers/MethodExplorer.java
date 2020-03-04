package slogo.Model.Explorers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Parsing.LanguageConverter;

import java.util.HashMap;

public class MethodExplorer {

    private ObservableMap<String, UserDefinedInstructionCommand> methodMap;

    public MethodExplorer(){
        methodMap = FXCollections.observableMap(new HashMap<>());
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

    public void convertLanguage(String oldLanguage, String newLanguage){
        LanguageConverter languageConverter = new LanguageConverter(oldLanguage, newLanguage);
        for (String name: methodMap.keySet()){
            methodMap.get(name).translateCommands(languageConverter);
        }
    }

    public ObservableMap<String, UserDefinedInstructionCommand> getMethodNames(){
        return methodMap;
    }

}
