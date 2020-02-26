package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.Commands.Command;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Command> pastMethods;
    private ObservableList<String> pastInputs;

    public History(){
        pastMethods = new ArrayList<>();
        pastInputs= FXCollections.observableList(new ArrayList<>());
    }

    /**
     * Adds command to history
     * @param element
     */
    public void addComand(Command element){
        pastMethods.add(element);
    }
    public void removeCommand(Command element){
        pastMethods.remove(element);
    }
    public void addInput(String str){
        pastInputs.add(str);
    }
    public void removeInput(String str){
        pastInputs.remove(str);
    }
    public ObservableList getInputs(){ return pastInputs;}

}
