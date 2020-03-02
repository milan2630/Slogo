package slogo.Model.Explorers.Variables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.Model.ErrorHandling.ParsingException;

import java.util.ArrayList;
import java.util.List;

public class VariableExplorer{
    private ObservableList<Variable> myDisplayVariables;
    public VariableExplorer(){
        myDisplayVariables = FXCollections.observableList(new ArrayList<>());
    }

    public void addVariable(Variable value) throws ParsingException {
        if(getVarNames().contains(value.getName())){
            removeVariableByName(value.getName());
        }
        myDisplayVariables.add(value);
    }
    public void removeVariable(Variable value){
        myDisplayVariables.remove(value.getName());
    }

    public Variable<Double> addDoubleVarByName(String name, double val) throws ParsingException {
        Variable<Double> var = new DoubleVariable(name, val);
        this.addVariable(var);
        return var;
    }

    public void removeVariableByName(String name){
        if(getVariable(name) != null){
            myDisplayVariables.remove(getVariable(name));
        }
    }

    public void removeVariablesByNames(List<String> names){
        for(String name: names) {
            removeVariableByName(name);
        }
    }

    private List<String> getVarNames(){
        List<String> names = new ArrayList<>();
        for(Variable var: myDisplayVariables){
            names.add(var.getName());
        }
        return names;
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
