package view;

import java.beans.EventHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.FrontEndExternal;
import slogo.Variable;

public class Visualizer implements PropertyChangeListener, FrontEndExternal {

  private static final String DEFAULT_LANGUAGE = "English";
  private static ResourceBundle resourceBundle;
  private String language;
  private Display display;
  private SettingView settingView;
  private HistoryView historyView;
  private Terminal terminal;
  private String consoleString;

  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 500;

  public Visualizer(Stage stage, String language) {
    this.language = language;
    setTitle(stage);

    AnchorPane root = new AnchorPane();

    terminal = new Terminal(language);
    terminal.addChangeListener(this);

    display = new Display();
    addPanesToRoot(root);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();
    System.out.println(display.getPane().getWidth());
  }

  private void addPanesToRoot(AnchorPane root) {
    Node terminalNode = terminal.getPane();
    AnchorPane.setBottomAnchor(terminalNode, 0.0);
    AnchorPane.setLeftAnchor(terminalNode,0.0);
    AnchorPane.setRightAnchor(terminalNode,0.0);

    Pane displayNode = display.getPane();
    AnchorPane.setTopAnchor(displayNode,0.0);
    AnchorPane.setBottomAnchor(displayNode,terminal.getHeight());
    AnchorPane.setRightAnchor(displayNode,0.0);
    AnchorPane.setLeftAnchor(displayNode, 250.0);
    //TODO replace 200 with controller width


    //Adding Tabs
    TabPane tabNode = new TabPane();
    AnchorPane.setTopAnchor(tabNode, 0.0);
    AnchorPane.setBottomAnchor(tabNode, terminal.getHeight());
    AnchorPane.setLeftAnchor(tabNode, 0.0);
    AnchorPane.setRightAnchor(tabNode,550.0);

    //Adding History Tab
    ObservableList<String> list= FXCollections.observableList(new ArrayList<>());
    historyView = new HistoryView(language, list);
    tabNode.getTabs().add(historyView.getTab());
    historyView.addChangeListener(this);
    list.add("a");
    list.add("b");
    list.add("c");

    //Adding Variable Tab
    ObservableList<Variable> list2= FXCollections.observableList(new ArrayList<>());
    VariableView variableView = new VariableView(language, FXCollections.observableList(list2));
    tabNode.getTabs().add(variableView.getTab());

    //Adding Setting Tab
    settingView = new SettingView(language);
    tabNode.getTabs().add(settingView.getTab());
    settingView.addChangeListener(this);

    root.getChildren().addAll(terminalNode,displayNode, tabNode);
    tabNode.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
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
   * @param evt
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    int r = new Random().nextInt((int) this.display.getPane().getWidth());
    int r2 = new Random().nextInt((int) this.display.getPane().getHeight());

    if (evt.getSource().equals(terminal) && evt.getPropertyName().equals("Run")){
      this.consoleString = evt.getNewValue().toString();
      display.moveTurtle(new Point2D(r,r2));
    }
    if (evt.getSource().equals(terminal) && evt.getPropertyName().equals("Reset")){
      display.resetPane();
    }
    if (evt.getSource().equals(settingView) && evt.getPropertyName().equals("Color")){
      display.setPenColor(Color.web(evt.getNewValue().toString()));
    }
    if (evt.getSource().equals(historyView) && evt.getPropertyName().equals("HistoryVariable")){
      terminal.setInputText(evt.getNewValue().toString());
    }
  }

  @Override
  public String getConsoleString() {
    return consoleString;
  }

  @Override
  public void updatePositions(double newX, double newY) {
    display.moveTurtle(new Point2D(newX,newY));
  }

  @Override
  public void updateHeading(double newHeading) {
    display.setTurtleHeading(newHeading);
  }

  @Override
  public void updatePenState(boolean penState) {
    display.setPenState(penState);
  }

  @Override
  public String getLanguage() {
    return language;
  }

  @Override
  public void displayError(Exception error) {

  }

  @Override
  public void createButton(EventHandler event, String property) {

  }
}
