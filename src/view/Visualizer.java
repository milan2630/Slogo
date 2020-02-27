package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.FrontEndExternal;
import slogo.ImmutableTurtle;

public class Visualizer implements FrontEndExternal, PropertyChangeListener {

  private static final String DEFAULT_LANGUAGE = "English";
  private static ResourceBundle resourceBundle;
  private String language;
  private Display display;
  private TabPaneView tabPaneView;
  private Terminal terminal;
  private String consoleString;

  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 500;

  public Visualizer(Stage stage, String language) {
    this.language = language;
    setTitle(stage);

    BorderPane root = new BorderPane();

    terminal = new Terminal(language);
    addTerminalChangeListener(this);

    display = new Display();

    tabPaneView = new TabPaneView(language);
    tabPaneView.addChangeSettingsListener(this);
    addPanesToRoot(root);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  public void addTerminalChangeListener(PropertyChangeListener newListener) {
    terminal.addChangeListener(newListener);
  }

  private void addPanesToRoot(BorderPane root) {
    Pane displayNode = display.getPane();
    Node terminalNode = terminal.getPane();

    TabPane tabNode = tabPaneView.getTabPane();

    root.setCenter(displayNode);
    BorderPane.setAlignment(tabNode, Pos.TOP_LEFT);
    root.setLeft(tabNode);
    root.setBottom(terminalNode);
  }

  private void setTitle(Stage stage) {
    try {
      resourceBundle = ResourceBundle
          .getBundle("resources/ui/" + language);
    } catch (MissingResourceException e) {
      this.language = DEFAULT_LANGUAGE;
      resourceBundle = ResourceBundle
          .getBundle("resources/ui/" + language);
    }
    stage.setTitle(resourceBundle.getString("Title"));
  }

  /**
   * Implements Observer Design pattern
   *
   * @param evt
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("Reset")) {
      display.resetPane();
    }
    if (evt.getPropertyName().equals("Pen Color")) {
      display.setPenColor(Color.web(evt.getNewValue().toString()));
    }
    if (evt.getPropertyName().equals("Background Color")) {
      display.setBackgroundColor(Color.web(evt.getNewValue().toString()));
    }
    if (evt.getPropertyName().equals("HistoryVariable")) {
      terminal.setInputText(evt.getNewValue().toString());
    }
  }

  @Override
  public String getConsoleString() {
    return consoleString;
  }

  @Override
  public void updateTurtle(List<ImmutableTurtle> turtleList) {
    for (ImmutableTurtle turtle : turtleList) {
      display.setTurtleHeading(turtle.getHeading());
      display.setPenState(turtle.getPenState());
      display.setTurtleVisibility(turtle.getShowing());
      display.moveTurtle(new Point2D(turtle.getX(), turtle.getY()));
    }
  }

  @Override
  public ImmutableTurtle getCurrentTurtle() {
    return display.getTurtleState();
  }

  @Override
  public String getLanguage() {
    return language;
  }

  @Override
  public void displayError(Exception error) {

  }

  public void bindHistory(String language, ObservableList inputs) {
    tabPaneView.createHistoryTab(language, inputs);
    tabPaneView.addChangeHistoryListener(this);
  }

  public void bindVariable(String language, ObservableList variables) {
    tabPaneView.createVariableTab(language, variables);
  }

  public void bindMethods(String language, ObservableMap methods) {
    tabPaneView.createMethodTab(language, methods);
  }
}
