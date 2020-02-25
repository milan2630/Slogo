package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.List;

public class VariableExplorer{
    private List<Variable> myVariables;
    private ObservableList<Variable> myDisplayVariables;
    public VariableExplorer(){
        myVariables = new ArrayList<>();
        myDisplayVariables = FXCollections.observableList(myVariables);
    }

    public void addVariable(Variable value){
        myVariables.add(value);
    }
    public void removeVariable(Variable value){
        myVariables.remove(value);
    }

    public Variable getVariable(String name){
        for(int i = 0; i < myVariables.size(); i++){
            if(myVariables.get(i).getName().equals(name)){
                return myVariables.get(i);
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
