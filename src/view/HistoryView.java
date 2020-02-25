package view;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    public HistoryView(String language, ObservableList<String> historyList){
        this.language= language;
        resourceBundle = ResourceBundle
                .getBundle("resources/ui/" + language);
        myTab = new Tab(resourceBundle.getString("HistoryTab"));
        history = historyList;
        list = new ListView<String>();
        listener = new ArrayList<>();
        setupTab();
    }
//    public TabPane getPane(){
//        TabPane tabPane = new TabPane();
//        Tab tab1 = new Tab("History");
//        Tab tab2 = new Tab("Variables");
//        Tab tab3 = new Tab("Methods");
//        //Label l = new Label("Hello");
//        //tab1.setContent(l);
//        tabPane.getTabs().addAll(tab1, tab2, tab3);
//        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//        return tabPane;
//    }
    public Tab getTab(){
        return myTab;
    }
    private void setupTab(){
        VBox vbox = new VBox();
        Button clearButton = new Button(resourceBundle.getString("ClearButton"));
        list.itemsProperty().bind(new SimpleObjectProperty<>(history));
        clearButton.setOnMouseClicked(e->emptyHistory());
        list.getSelectionModel().selectedItemProperty().addListener(e-> print());
        vbox.getChildren().addAll(clearButton, list);
        vbox.setAlignment(Pos.CENTER);
        myTab.setContent(vbox);
    }

    private void print() {
        System.out.println(list.getSelectionModel().getSelectedItem());
        notifyListeners("HistoryVariable","" , list.getSelectionModel().getSelectedItem() );
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

