package slogo.view;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.ReflectionException;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class TurtleTabView {
    private static ResourceBundle resourceBundle;
    private static ResourceBundle actionResources;
    private static final String RESOURCES_LAYOUT = "resources/UI/";
    private static final String RESOURCES_TURTLE_COLS = "resources/Layouts/turtlestab/";
    private String language;
    private Tab myTab;
    private Actions actions;
    private TableView<ImmutableTurtle> tableView;
    public TurtleTabView(String language, Actions actions){
        this.language = language;
        resourceBundle = ResourceBundle.getBundle(RESOURCES_LAYOUT+language);
        actionResources = ResourceBundle.getBundle(RESOURCES_TURTLE_COLS+language);
        myTab = new Tab(resourceBundle.getString("TurtleTab"));
        this.actions= actions;
        tableView= new TableView<>();
        setupTab();
    }

    private void setupTab() {
        VBox vbox = new VBox();
        List<String> columnList = Collections.list(actionResources.getKeys());
        for (String col: columnList){
            TableColumn column = createColumn(col);
            tableView.getColumns().add(column);
        }
        tableView.setEditable(true);
        tableView.refresh();
        vbox.getChildren().add(tableView);
        vbox.setAlignment(Pos.CENTER);
        myTab.setContent(vbox);
    }

    private TableColumn createColumn(String col) {
        TableColumn column = new TableColumn(col.toUpperCase());
        column.setCellValueFactory(new PropertyValueFactory<ImmutableTurtle,Object>(col));
        try {
            Method m = this.getClass().getDeclaredMethod(actionResources.getString(col), TableColumn.class);
            m.invoke(this, column);
        } catch (Exception e) {
            throw new ReflectionException("InvalidMethod", actionResources.getString(col));
        }
        return column;
    }

    private void addUneditableColumn(TableColumn c){
        //do nothing
    }

    private void addEditableColumn(TableColumn c){
        c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        c.setOnEditCommit(e-> editTurtleState((TableColumn.CellEditEvent)e));
    }

    private void editTurtleState(TableColumn.CellEditEvent e) {
        if (e != null) {
            ImmutableTurtle t = tableView.getSelectionModel().getSelectedItem();
            actions.handleTurtleState(t.getX() + "", e.getNewValue() + "");
        }
    }

    /**
     * returns Tab for states of turtles
     * @return myTab
     */
    public Tab getTab(){ return myTab; }

    public void setTable(ImmutableTurtle turtle){
        List<ImmutableTurtle> list = List.of(turtle);
        tableView.setItems(FXCollections.observableList(list));
    }
}
