package slogo.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import slogo.Model.Parsing.LanguageConverter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class HistoryView {
    private static ResourceBundle resourceBundle;
    private static final String RESOURCES_HISTORY = "resources/Layouts/SettingsTab/";
    private static final String RESOURCES_LAYOUT = "resources/UI/";
    private String language;
    private Tab myTab;
    private ObservableList<String> history;
    private ListView<String> list;
    private Actions actions;
    public HistoryView(String language, ObservableList<String> historyList, Actions actions){
        this.language= language;
        resourceBundle = ResourceBundle.getBundle(RESOURCES_LAYOUT + language);
        myTab = new Tab(resourceBundle.getString("HistoryTab"));
        history = historyList;
        list = new ListView<String>();
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
    private void emptyHistory() {
        if (!history.isEmpty()) {
            history.clear();
        }
    }
    public void setLanguage(String newLanguage) {
        LanguageConverter languageConverter = new LanguageConverter(language);
        for (int i =0; i<history.size(); i++){
            String oldString = history.get(i);
            history.set(i ,languageConverter.translateString(oldString, newLanguage));
        }
        language = newLanguage;
    }
}