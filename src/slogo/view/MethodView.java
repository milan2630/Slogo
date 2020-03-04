package slogo.view;

import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class MethodView {
    private static ResourceBundle resourceBundle;
    private String language;
    private Tab myTab;
    private ObservableMap<String, UserDefinedInstructionCommand> methods;
    private ListView<String> listView;
    private List<PropertyChangeListener> listener;
    private Actions actions;
    public MethodView(String language, ObservableMap savedMethodNames, Actions actions){
        this.language = language;
        methods = savedMethodNames;
        resourceBundle = ResourceBundle.getBundle("resources/UI/" + language);
        this.actions = actions;
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
        listView.setOnMouseClicked(e -> displayMethod());
    }

    private void displayMethod() {
        String s = listView.getSelectionModel().getSelectedItem();
        int space = s.indexOf('\t');
        String methodName = s.substring(0, space);
        String methodArgs = s.substring(space+1);
        int parameters = Integer.parseInt(methodArgs);
        if (parameters > 0 ) {
            String display = determineDisplay(methodName, parameters);
            displayDialogBox(methodName, display, parameters);
        }
        else
            setTerminal(methodName, null);
    }

    private void displayDialogBox(String methodName, String display, int parameters) {
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
        dialog.setResultConverter(new Callback<ButtonType, List<String>>() {
            @Override
            public List<String> call(ButtonType b) {
                if (b == ButtonType.OK) {
                    List<String> parameters = new ArrayList<>();
                    for (Node n: vbox.getChildren()){
                        TextField f = (TextField) n;
                        parameters.add(f.getText());
                    }
                    return parameters;
                }

                return null;
            }
        });
        Optional<List<String>> result = dialog.showAndWait();
        if (result.isPresent()){
            setTerminal(methodName, result.get());
        }
    }

    private void setTerminal(String methodName, List<String> parameters) {
        String display = methodName;
        if (parameters != null){
            for (String arg: parameters)
                display+=" "+arg;
        }

        actions.handleMethodDisplay(display);
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
