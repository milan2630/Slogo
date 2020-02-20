package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.List;

public class VariableExplorer{
    private List<Variable> myVariables;
    private ObservableMap<String,String> myDisplayVariables;
    public VariableExplorer(){
        myVariables = new ArrayList<>();
        myDisplayVariables = FXCollections.observableHashMap();
    }

    public void addVariable(Variable value){
        myVariables.add(value);
        myDisplayVariables.put(value.getName(), ""+value.getValue());
    }

    /**
     * returns myDisplayVariables
     * @return ObservableMap myDisplayVariables
     */
    public ObservableMap getMap(){
        return myDisplayVariables;
    }

}
