package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ResourceBundle;

public class HistoryView {
    private static ResourceBundle resourceBundle;
    private String language;
    private Tab myTab;
    private ObservableList<String> history;
    public HistoryView(String language, ObservableList<String> historyList){
        this.language= language;
        resourceBundle = ResourceBundle
                .getBundle("resources/ui/" + language);
        myTab = new Tab(resourceBundle.getString("HistoryTab"));
        history = historyList;
        setupTab();
    }
    public TabPane getPane(){
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("History");
        Tab tab2 = new Tab("Variables");
        Tab tab3 = new Tab("Methods");
        //Label l = new Label("Hello");
        //tab1.setContent(l);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabPane;
    }
    public Tab getTab(){
        return myTab;
    }
    private void setupTab(){
        ListView<String> list = new ListView<String>();
        list.itemsProperty().bind(new SimpleObjectProperty<>(history));
        myTab.setContent(list);
    }
}
