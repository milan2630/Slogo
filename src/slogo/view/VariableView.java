package slogo.view;

import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import slogo.Model.Explorers.Variables.Variable;

import java.util.ResourceBundle;

public class VariableView <E>{
    private ObservableList<Variable> variables;
    private Tab myTab;
    private TableView<Variable> tableView;
    private ListView<String> listView;
    private static ResourceBundle resourceBundle;
    public VariableView(String language, ObservableList variableList){
        resourceBundle = ResourceBundle.getBundle("resources/UI/" + language);
        variables=variableList;
        //listView = new ListView();
        myTab = new Tab(resourceBundle.getString("VariableTab"));
        this.tableView = new TableView<Variable>(variables);
        setupTab();
    }

    private void handle() {
        tableView.refresh();
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
        //setupListView();
        vbox.getChildren().addAll(clear, tableView);
        vbox.setAlignment(Pos.CENTER);
        myTab.setContent(vbox);
    }
    private void setupListView() {
        //listView.itemsProperty().bind(new SimpleObjectProperty<>(variables));
    }


    private void handle2(ListChangeListener.Change c) {
        listView.getItems().add(variables.size()+"");
        //System.out.println(variables.get(variables.size()-1).getName());
    }

    private void setupTableView() {
        tableView.setEditable(false);
        TableColumn variableNameCol = new TableColumn(resourceBundle.getString("VariableTab"));
        TableColumn valueCol = new TableColumn(resourceBundle.getString("Value"));
        variableNameCol.setCellValueFactory(new PropertyValueFactory<Variable,String>("Name"));
        valueCol.setCellValueFactory(new PropertyValueFactory<Variable,E>("Value"));
        tableView.setItems(variables);
        tableView.getColumns().addAll(variableNameCol, valueCol);
        tableView.refresh();
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
        //tableView.refresh();
    }
    public void refreshTable(){
        tableView.refresh();
    }
}
