package slogo.view;

import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MethodView {
    private static ResourceBundle resourceBundle;
    private String language;
    private Tab myTab;
    private ObservableMap<String, UserDefinedInstructionCommand> methods;
    private ListView<String> listView;
    private List<PropertyChangeListener> listener;
    public MethodView(String language, ObservableMap savedMethodNames){
        this.language = language;
        methods = savedMethodNames;
        resourceBundle = ResourceBundle.getBundle("resources/UI/" + language);
        listView = new ListView<String>();
        listener = new ArrayList<>();
        myTab = new Tab(resourceBundle.getString("MethodTab"));
        setupTab();
    }

    private void setupTab() {
        VBox vbox = new VBox();
        Button clearButton = makeClearButton();
        setupListView();
        vbox.getChildren().addAll(clearButton, listView);
        vbox.setAlignment(Pos.CENTER);
        myTab.setContent(vbox);
    }

    private void setupListView() {
        methods.addListener((MapChangeListener.Change<? extends String, ? extends UserDefinedInstructionCommand> c)-> handle(c));
        listView.getSelectionModel().selectedItemProperty().addListener(e-> displayMethod());
    }

    private void displayMethod() {
        String s = listView.getSelectionModel().getSelectedItem();
        int space = s.indexOf(" ");
        String methodName = s.substring(0, space);
        methodName+=" [ ";
        String methodArgs = s.substring(space);
        String args = methodArgs.replace(" ", "");
        int parameters = Integer.parseInt(args);
        for (int i=0; i< parameters; i++){
            methodName +="? ";
        }
        methodName = "]";
        notifyListeners("MethodDisplay",listView.getSelectionModel().getSelectedItem() , methodName);
    }
    private void notifyListeners(String property, String oldValue, String newValue) {
        //TODO make single prop listener
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }
    private void handle(MapChangeListener.Change<? extends String,? extends UserDefinedInstructionCommand> c) {
        if (c.wasAdded()){
            String str = ""+methods.get(c.getKey()).getNumArguments();
            listView.getItems().add(c.getKey()+"\t"+str);
        }
        if (c.wasRemoved()){
            listView.getItems().remove(c.getKey());
        }
    }

    private Button makeClearButton() {
        Button clearButton = new Button(resourceBundle.getString("ClearButton"));
        clearButton.setOnAction(e->clearSavedMethods());
        return clearButton;
    }

    private void clearSavedMethods() {
        if (!methods.isEmpty()){
            methods.clear();
            listView.getItems().clear();
        }
    }
    public Tab getTab(){ return myTab;}

}
