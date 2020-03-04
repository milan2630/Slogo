package slogo.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class HistoryView {
    private static ResourceBundle resourceBundle;
    private String language;
    private Tab myTab;
    private ObservableList<String> history;
    private ListView<String> list;
    private List<PropertyChangeListener> listener;
    private Actions actions;
    public HistoryView(String language, ObservableList<String> historyList, Actions actions){
        this.language= language;
        resourceBundle = ResourceBundle
                .getBundle("resources/UI/" + language);
        myTab = new Tab(resourceBundle.getString("HistoryTab"));
        history = historyList;
        list = new ListView<>();
        listener = new ArrayList<>();
        this.actions = actions;
        setupTab();
    }
    public Tab getTab(){
        return myTab;
    }
    private void setupTab(){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        Button clearButton = new Button(resourceBundle.getString("ClearButton"));
        list.itemsProperty().bind(new SimpleObjectProperty<>(history));
        clearButton.setOnMouseClicked(e->emptyHistory());
        list.getSelectionModel().selectedItemProperty().addListener(e-> print());
        vbox.getChildren().addAll(clearButton, list);

        myTab.setContent(vbox);
    }

    private void print() {
        actions.handleHistoryVariable(list.getSelectionModel().getSelectedItem());
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

    private void notifyListeners(String property, String oldValue, String newValue) {
        //TODO make single prop listener
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }
    private void emptyHistory() {
        if (!history.isEmpty()) {
            history.clear();
        }
//        for (String str: history)
//            history.remove(str);
    }
}

