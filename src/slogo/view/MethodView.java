package slogo.view;

import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        int space = s.indexOf('\t');
        String methodName = s.substring(0, space);
        String methodArgs = s.substring(space+1);
        int parameters = Integer.parseInt(methodArgs);
        if (parameters > 0 ) {
            String display = determineDisplay(methodName, parameters);
            displayDialogBox(display, parameters);
        }
        //to green [ ] [ fd 50 ]
    }

    private void displayDialogBox(String display, int parameters) {
        Dialog dialog = new Dialog();
        //TODO make not hard coded
        VBox vbox = new VBox();
        for (int i =0; i<parameters; i++){
            TextField input = new TextField();
            int j = i+1;
            input.setPromptText("Parameter "+j);
            vbox.getChildren().add(input);
        }
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setTitle("o7planning");
        dialog.setHeaderText(display);
        vbox.setSpacing(10);
        dialog.getDialogPane().setContent(vbox);
//        Optional<List<String>> result = dialog.showAndWait();
//        result.ifPresent(name -> {
//            System.out.println(name);
//        });
    }

    private String determineDisplay(String name, int parameters){
        String result = name+ " [ ";
        for (int i=0; i< parameters; i++){
            result +="? ";
        }
        result += "]";
        return result;
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
