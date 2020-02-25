package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MethodView {
//    private static ResourceBundle resourceBundle;
//    private String language;
//    private Tab myTab;
//    private ObservableList<String> methods;
//    private ListView<String> listView;
//    private List<PropertyChangeListener> listener;
//    public MethodView(String language, ObservableList savedMethodNames){
//        this.language = language;
//        methods = savedMethodNames;
//        resourceBundle = ResourceBundle.getBundle("resources/ui/" + language);
//        listView = new ListView<String>();
//        listener = new ArrayList<>();
//        setupTab();
//    }
//
//    private void setupTab() {
//        VBox vbox = new VBox();
//        Button clearButton = makeClearButton();
//        setupListView();
//        vbox.getChildren().addAll(clearButton, listView);
//        vbox.setAlignment(Pos.CENTER);
//        myTab.setContent(vbox);
//    }
//
//    private void setupListView() {
//        listView.itemsProperty().bind(new SimpleObjectProperty<>(methods));
//    }
//
//    private Button makeClearButton() {
//        Button clearButton = new Button(resourceBundle.getString("ClearButton"));
//        clearButton.setOnAction(e->clearSavedMethods());
//        return clearButton;
//    }
//
//    private void clearSavedMethods() {
//        if (!methods.isEmpty()){
//            methods.clear();
//        }
//    }

}
