package slogo.view;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.FrontEndExternal;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.view.terminal.Terminal;
import slogo.view.turtledisplay.Display;

public class Visualizer implements FrontEndExternal {

  private static final String DEFAULT_LANGUAGE = "English";
  private static ResourceBundle resourceBundle;
  private String language;
  private Display display;
  private TabPaneView tabPaneView;
  private Terminal terminal;
  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 600;

  public Visualizer(Stage stage, String language, Actions actions) {
    this.language = language;
    setBundle();
    stage.setTitle(resourceBundle.getString("Title"));

    BorderPane root = new BorderPane();

    terminal = new Terminal(language, actions);

    display = new Display();

    tabPaneView = new TabPaneView(language, actions);
    addPanesToRoot(root);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add("resources/Styles/default.css");
    stage.setScene(scene);
    stage.show();
  }

  private void addPanesToRoot(BorderPane root) {
    Pane displayNode = display;
    displayNode.getStyleClass().add("display");

    TabPane tabNode = tabPaneView.getTabPane();

    root.setCenter(displayNode);
    BorderPane.setAlignment(tabNode, Pos.TOP_LEFT);
    root.setLeft(tabNode);
    root.setBottom(terminal);
  }

  private void setBundle() {
    try {
      resourceBundle = ResourceBundle
          .getBundle("resources/UI/" + language);
    } catch (MissingResourceException e) {
      this.language = DEFAULT_LANGUAGE;
      resourceBundle = ResourceBundle
          .getBundle("resources/UI/" + language);
    }
  }

  public void setPenColor(Color color) {
    display.setPenColor(color);
  }

  public void setBackgroundColor(Color color) {
    display.setBackgroundColor(color);
  }

  public void setTurtleImage(String filename) {
    display.setTurtleImage(filename);
  }

  public void setInputText(String text) {
    terminal.setInputText(text);
  }

  public void resetTrail() {
    display.resetPane();
  }

  public void setPenThickness(Double thickness) {
    display.setPenThickness(thickness);
  }

  public void setPenStatus(int active) {
    display.setPenState(active);
  }

  @Override
  public void updateTurtle(List<ImmutableTurtle> turtleList) throws ParsingException {
    for (ImmutableTurtle turtle : turtleList) {
      display.setTurtleHeading(turtle.getHeading());
      display.setPenState(turtle.getPenState());
      display.setTurtleVisibility(turtle.getShowing());
      tabPaneView.updateTurtleTab(turtle);
      if (checkTurtleOutOfBounds(turtle)) {
        throw new ParsingException("OutOfBoundsException", turtleList.indexOf(turtle));
      }
      display.moveTurtle(new Point2D(turtle.getX(), -1 * turtle.getY()));
    }
  }

  @Override
  public void displayError(Exception error) {
    terminal.setErrorText(error.getMessage());
  }

  @Override
  public void bindTabs(String language, ObservableList history, ObservableList variables,
      ObservableMap methods, ObservableList palette) {
    tabPaneView.createHistoryTab(language, history);
    tabPaneView.createMethodTab(language, methods);
    tabPaneView.createVariableTab(language, variables);
    tabPaneView.createPaletteTab(language, palette);
  }

  @Override
  public void setHistoryLanguage(String newLanguage) {
    tabPaneView.setHistoryLanguage(newLanguage);
  }

  private Boolean checkTurtleOutOfBounds(ImmutableTurtle turtle) {
    return turtle.getX() > display.getWidth() / 2
        || turtle.getX() < -1 * display.getWidth() / 2 ||
        turtle.getY() > display.getHeight() / 2 || turtle.getY() < -1 *
        display.getHeight() / 2;
  }
}
