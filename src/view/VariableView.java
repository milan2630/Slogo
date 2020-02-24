package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

import java.util.ResourceBundle;

public class VariableView {
    private ObservableList<String> variables;
    private Tab myTab;
    private static ResourceBundle resourceBundle;
    public VariableView(String language, ObservableList list){
        resourceBundle = ResourceBundle
                .getBundle("resources/ui/" + language);
        variables=list;
        myTab = new Tab(resourceBundle.getString("VariableTab"));
        setupTab();
    }

    /**
     * returns Tab for Variables
     * @return myTab
     */
    public Tab getTab(){
        return myTab;
    }

    private void setupTab() {
        ListView<String> list = new ListView<String>();
        list.itemsProperty().bind(new SimpleObjectProperty<>(variables));
        myTab.setContent(list);
    }

}
