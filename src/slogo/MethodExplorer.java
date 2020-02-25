package slogo;

import java.util.HashMap;
import java.util.Map;

public class MethodExplorer {

    private Map<String, SlogoMethod> methodMap;

    public MethodExplorer(){
        methodMap = new HashMap<>();
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

}
