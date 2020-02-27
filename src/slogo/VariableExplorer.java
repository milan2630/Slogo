package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.List;

public class VariableExplorer{
    private ObservableList<Variable> myDisplayVariables;
    public VariableExplorer(){
        myDisplayVariables = FXCollections.observableList(new ArrayList<>());
    }

    public void addVariable(Variable value){
        myDisplayVariables.add(value);
    }
    public void removeVariable(Variable value){
        myDisplayVariables.remove(value.getName());
    }

    public void removeVariableByName(String name){
        if(getVariable(name) != null){
            myDisplayVariables.remove(getVariable(name));
        }
    }

    public Variable getVariable(String name){
        for(int i = 0; i < myDisplayVariables.size(); i++){
            if(myDisplayVariables.get(i).getName().equals(name)){
                return myDisplayVariables.get(i);
            }
        }
        return null;
    }

    /**
     * returns myDisplayVariables
     * @return ObservableMap myDisplayVariables
     */
    public ObservableList<Variable> getDisplayVariables(){
        return myDisplayVariables;
    }

}
