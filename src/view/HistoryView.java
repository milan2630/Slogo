package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HistoryView {
    public TabPane getPane(){
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("History");
        Tab tab2 = new Tab("Variables");
        Tab tab3 = new Tab("Methods");

        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabPane;
    }
}
