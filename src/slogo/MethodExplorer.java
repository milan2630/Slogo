package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

import java.util.HashMap;
import java.util.Map;

public class MethodExplorer {

    private ObservableMap<String, SlogoMethod> methodMap;

    public MethodExplorer(){
        methodMap = FXCollections.observableMap(new HashMap<>());
    }

    public void addMethod(SlogoMethod method){
        methodMap.put(method.getName(), method);
    }

    public SlogoMethod getMethod(String name){
        if(methodMap.keySet().contains(name)){
            return methodMap.get(name);
        }
        return null;
    }

    public ObservableMap getMethodNames(){
        return methodMap;
    }

}
