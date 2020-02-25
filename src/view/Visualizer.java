package view;

import java.awt.Point;
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
import javafx.stage.Stage;
import slogo.Variable;

public class Visualizer implements PropertyChangeListener {

  private static final String DEFAULT_LANGUAGE = "English";
  private static ResourceBundle resourceBundle;
  private String language;
  private Display display;
  private Terminal terminal;

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
    System.out.println(displayNode.getWidth());
    //TODO replace 200 with controller width


    //Adding Tabs
    TabPane tabNode = new TabPane();
    AnchorPane.setTopAnchor(tabNode, 0.0);
    AnchorPane.setBottomAnchor(tabNode, terminal.getHeight());
    AnchorPane.setLeftAnchor(tabNode, 0.0);
    AnchorPane.setRightAnchor(tabNode,550.0);

    //Adding History Tab
    ObservableList<String> list= FXCollections.observableList(new ArrayList<>());
    HistoryView historyView = new HistoryView(language, list);
    tabNode.getTabs().add(historyView.getTab());
    list.add("a");
    list.add("b");
    list.add("c");

    //Adding Variable Tab
    ObservableList<Variable> list2= FXCollections.observableList(new ArrayList<>());
    VariableView variableView = new VariableView(language, FXCollections.observableList(list2));
    tabNode.getTabs().add(variableView.getTab());

    //Adding Setting Tab
    SettingView settingView = new SettingView(language);
    tabNode.getTabs().add(settingView.getTab());

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
    int r = new Random().nextInt(600);
    int r2 = new Random().nextInt(400);

    System.out.println(evt.getNewValue());

    if (evt.getPropertyName().equals("Run")){
      display.moveTurtle(new Point2D(r,r2));
    }
    else {
      display.resetPane();
    }

  }
}
