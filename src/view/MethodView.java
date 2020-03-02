package view;

import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import slogo.Model.Commands.ControlStructures.UserDefinedInstructionCommand;

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