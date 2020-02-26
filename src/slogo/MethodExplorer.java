package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;

public class MethodExplorer {

    private ObservableMap<String, UserDefinedInstructionCommand> methodMap;

    public MethodExplorer(){
        methodMap = FXCollections.observableMap(new HashMap<>());
    }

    public void addMethod(UserDefinedInstructionCommand method){
        methodMap.put(method.getName(), method);
    }

    public UserDefinedInstructionCommand getMethod(String name){
        if(methodMap.keySet().contains(name)){
            return methodMap.get(name);
        }
        return null;
    }

    public ObservableMap<String, UserDefinedInstructionCommand> getMethodNames(){
        return methodMap;
    }

}
