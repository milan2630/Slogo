package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import slogo.*;

import java.util.List;
import java.util.ResourceBundle;

public class VariableView <E>{
    private ObservableList<Variable> variables;
    private Tab myTab;
    private TableView<Variable> tableView;
    private static ResourceBundle resourceBundle;
    public VariableView(String language, ObservableList variableList){
        resourceBundle = ResourceBundle.getBundle("resources/ui/" + language);
        variables=variableList;
        myTab = new Tab(resourceBundle.getString("VariableTab"));
        this.tableView = new TableView<>();
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
        VBox vbox = new VBox();
        Button clear = makeClearButton();
        setupTableView();
        vbox.getChildren().addAll(clear, tableView);
        variables.add(new DoubleVariable("double", 2.0));
        variables.add(new IntegerVariable("int", 2));
        variables.add(new CharacterVariable("character", 'c'));
        variables.add(new StringVariable("String", "s"));
        vbox.setAlignment(Pos.CENTER);
        myTab.setContent(vbox);
    }

    private void setupTableView() {
        tableView.setEditable(false);
        TableColumn variableNameCol = new TableColumn(resourceBundle.getString("VariableTab"));
        TableColumn valueCol = new TableColumn(resourceBundle.getString("Value"));
        variableNameCol.setCellValueFactory(new PropertyValueFactory<Variable,String>("Name"));
        valueCol.setCellValueFactory(new PropertyValueFactory<Variable,E>("Value"));
        tableView.setItems(variables);
        tableView.getColumns().addAll(variableNameCol, valueCol);
    }

    private Button makeClearButton() {
        Button clearButton = new Button(resourceBundle.getString("ClearButton"));
        clearButton.setOnAction(e->emptyVariables());
        return clearButton;
    }

    private void emptyVariables() {
        if (!variables.isEmpty()) {
            variables.clear();
        }
    }

}
