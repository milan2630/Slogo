package slogo.view.tabdisplay;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.ReflectionException;
import slogo.Controller.Actions;

import java.lang.reflect.Method;
import java.util.*;

public class TurtleTabView {

  private ResourceBundle resourceBundle;
  private ResourceBundle actionResources;
  private ResourceBundle layoutResources;
  private static final String RESOURCES_PATH = "resources/UI/";
  private static final String RESOURCE_LAYOUTS = RESOURCES_PATH + "Layouts";
  private static final String METHODS_PATH = RESOURCES_PATH + "ReflectionMethods";
  private Map<Double, ImmutableTurtle> allTurtles;
  private List<ImmutableTurtle> inactiveTurtles;
  private LanguageHandler languageHandler;
  private Tab myTab;
  private Actions actions;

  private final int ERROR_SIZE = 9;
  private TableView<ImmutableTurtle> tableView;

  public TurtleTabView(LanguageHandler language, Actions actions) {
    languageHandler = language;
    resourceBundle = ResourceBundle.getBundle(RESOURCES_PATH + language.getLanguage());
    layoutResources = ResourceBundle.getBundle(RESOURCE_LAYOUTS);
    actionResources = ResourceBundle.getBundle(METHODS_PATH);
    myTab = new Tab(resourceBundle.getString("TurtleTab"));
    this.actions = actions;
    allTurtles = new HashMap<>();
    tableView = new TableView<>();
    setupTab();
  }

  private void setupTab() {
    VBox vbox = new VBox();
    String[] cols = layoutResources.getString("TurtleTabView").split(",");
    for (String col : cols) {
      TableColumn column = createColumn(col);
      tableView.getColumns().add(column);
    }
    tableView.setEditable(false);
    tableView.refresh();
    vbox.getChildren().add(tableView);
    vbox.setAlignment(Pos.CENTER);
    myTab.setContent(vbox);
  }

  private TableColumn createColumn(String col) {
    TableColumn column = new TableColumn(col.toUpperCase());
    try {
      Method m = this.getClass()
          .getDeclaredMethod(actionResources.getString(col), TableColumn.class, String.class);
      m.invoke(this, column, col);
    } catch (Exception e) {
      throw new ReflectionException("InvalidMethod", actionResources.getString(col));
    }
    return column;
  }

  private void addUneditableColumn(TableColumn c, String col) {
    c.setCellValueFactory(new PropertyValueFactory<ImmutableTurtle, Object>(col));
  }

  private void addSortedColumn(TableColumn c, String col) {
    addUneditableColumn(c, col);
    tableView.getSortOrder().add(c);
  }

  private void addCheckBox(TableColumn c, String col) {
    c.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<ImmutableTurtle, Boolean>, ObservableValue<Boolean>>() {
          @Override
          public ObservableValue<Boolean> call(
              TableColumn.CellDataFeatures<ImmutableTurtle, Boolean> param) {
            return new SimpleBooleanProperty((int) (param.getValue().isActive()) == 1);
          }
        });
    CheckBoxTableCell cell = new CheckBoxTableCell();
    c.setCellFactory(cell.forTableColumn(c));
    tableView.setOnMouseClicked(e -> editTurtleState(cell));
  }

  private void editTurtleState(CheckBoxTableCell c) {
    LanguageHandler l = new LanguageHandler("English");
    ImmutableTurtle t = tableView.getSelectionModel().getSelectedItem();
    List<ImmutableTurtle> turtles = tableView.getItems();
    if (t == null || turtles.size() == 1) {
      return;
    }
    String tell = createTellCommand(t, turtles);
    if (tell.length() <= ERROR_SIZE) {
      return;
    }
    actions.handleCommand(tell);
    tableView.getSelectionModel().select(-1);
    tableView.sort();
  }

  private String createTellCommand(ImmutableTurtle t, List<ImmutableTurtle> turtles) {
    String tell = languageHandler.getLanguageCommand("Tell") + " [ ";
    for (ImmutableTurtle turtle : turtles) {
      if (turtle.getID() != t.getID() && (int) (turtle.isActive()) == 1) {
        tell += "" + turtle.getID() + " ";
      }
    }
    if (t.isActive() == 0) {
      tell += t.getID() + " ";
    }
    tell += " ]";
    return tell;
  }


  /**
   * returns Tab for states of turtles
   *
   * @return myTab
   */
  public Tab getTab() {
    return myTab;
  }

  /**
   * reset table after each run with states of each turtle
   *
   * @param turtleList list of turtles
   */
  public void setTable(Map<Double, List<ImmutableTurtle>> turtleList) {
    List<ImmutableTurtle> completedTurtles = new ArrayList<>();
    findInactiveTurtles(turtleList);
    int max = findMaxSteps(turtleList);
    for (int j = 0; j < max; j++) {
      tableView.getItems().clear();
      for (double i : turtleList.keySet()) {
        if (turtleList.get(i).size() - 1 == j) {
          completedTurtles.add(turtleList.get(i).get(j));
          allTurtles.putIfAbsent(i, turtleList.get(i).get(j));
        } else if (max < turtleList.get(i).size()) {
          tableView.getItems().add(turtleList.get(i).get(j));
        }
      }
      for (ImmutableTurtle t : completedTurtles) {
        tableView.getItems().add(t);
      }
    }
    tableView.sort();
  }


  private void findInactiveTurtles(Map<Double, List<ImmutableTurtle>> turtleList) {
    for (Double i : allTurtles.keySet()) {
      if (!turtleList.containsKey(i)) {
        inactiveTurtles.add(allTurtles.get(i));
      }
    }
  }

  private int findMaxSteps(Map<Double, List<ImmutableTurtle>> turtleList) {
    int max = -1;
    for (double i : turtleList.keySet()) {
      if (turtleList.get(i).size() > max) {
        max = turtleList.get(i).size();
      }
    }
    return max;
  }

}
