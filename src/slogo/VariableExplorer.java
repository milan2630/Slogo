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
    /**
     * returns myDisplayVariables
     * @return ObservableMap myDisplayVariables
     */
    public ObservableList<Variable> getDisplayVariables(){
        return myDisplayVariables;
    }

}
